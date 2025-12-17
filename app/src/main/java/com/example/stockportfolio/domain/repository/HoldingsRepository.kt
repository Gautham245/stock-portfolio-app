package com.example.stockportfolio.domain.repository


import com.example.stockportfolio.domain.model.Holding

interface HoldingsRepository {
    suspend fun getHoldings(): List<Holding>
}
