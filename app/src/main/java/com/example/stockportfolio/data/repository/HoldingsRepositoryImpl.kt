package com.example.stockportfolio.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.stockportfolio.data.local.HoldingsDao
import com.example.stockportfolio.data.mapper.toDomain
import com.example.stockportfolio.data.mapper.toEntity
import com.example.stockportfolio.data.remote.HoldingsApi
import com.example.stockportfolio.domain.model.Holding
import com.example.stockportfolio.domain.repository.HoldingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.IOException

class HoldingsRepositoryImpl(
    private val api: HoldingsApi,
    private val dao: HoldingsDao
) : HoldingsRepository {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun getHoldings(): Flow<Result<List<Holding>>> = flow {

        val cached = dao.getAllHoldings().map { it.toDomain() }
        if (cached.isNotEmpty()) {
            emit(Result.success(cached))
        }

        try {
            val response = api.getHoldings()

            val remoteHoldings = response.data.userHolding.map { dto ->
                Holding(
                    symbol = dto.symbol,
                    quantity = dto.quantity,
                    ltp = dto.ltp,
                    avgPrice = dto.avgPrice,
                    close = dto.close
                )
            }

            dao.clearHoldings()
            dao.insertHoldings(remoteHoldings.map { it.toEntity() })

            emit(Result.success(remoteHoldings))

        } catch (e: IOException) {
            if (cached.isNotEmpty()) {
                emit(Result.success(cached))
            } else {
                emit(Result.failure(Exception("No internet connection")))
            }

        } catch (e: HttpException) {
            if (cached.isNotEmpty()) {
                emit(Result.success(cached))
            } else {
                emit(Result.failure(Exception("Server error ${e.message}")))
            }

        } catch (e: Exception) {
            if (cached.isNotEmpty()) {
                emit(Result.success(cached))
            } else {
                emit(Result.failure(Exception("Unexpected error")))
            }
        }

    }.flowOn(Dispatchers.IO)
}

