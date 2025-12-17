package com.example.stockportfolio.data.repository

import com.example.stockportfolio.data.remote.HoldingsApi
import com.example.stockportfolio.domain.model.Holding
import com.example.stockportfolio.domain.repository.HoldingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HoldingsRepositoryImpl(
    private val api: HoldingsApi
) : HoldingsRepository {

    override suspend fun getHoldings(): List<Holding> = withContext(Dispatchers.IO) {
        val response = api.getHoldings()
        response.data.userHolding.map { dto ->
            Holding(
                symbol = dto.symbol,
                quantity = dto.quantity,
                ltp = dto.ltp,
                avgPrice = dto.avgPrice,
                close = dto.close
            )
        }
    }
}
