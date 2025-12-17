package com.example.stockportfolio.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HoldingsResponseDto(
    @SerialName("data")
    val data: HoldingsDataDto
)

@Serializable
data class HoldingsDataDto(
    @SerialName("userHolding")
    val userHolding: List<HoldingDto>
)

@Serializable
data class HoldingDto(
    @SerialName("symbol")
    val symbol: String,
    @SerialName("quantity")
    val quantity: Int,
    @SerialName("ltp")
    val ltp: Double,
    @SerialName("avgPrice")
    val avgPrice: Double,
    @SerialName("close")
    val close: Double
)
