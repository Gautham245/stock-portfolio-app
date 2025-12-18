package com.example.stockportfolio.domain.repository


import com.example.stockportfolio.domain.model.Holding
import kotlinx.coroutines.flow.Flow

interface HoldingsRepository {
    fun getHoldings(): Flow<Result<List<Holding>>>
}
