package com.example.stockportfolio.data.remote


import com.example.stockportfolio.data.dto.HoldingsResponseDto
import retrofit2.http.GET

interface HoldingsApi {
    @GET("/")
    suspend fun getHoldings(): HoldingsResponseDto
}
