package com.example.stockportfolio.data.mapper


import com.example.stockportfolio.data.local.enitity.HoldingEntity
import com.example.stockportfolio.domain.model.Holding

fun HoldingEntity.toDomain(): Holding =
    Holding(
        symbol = symbol,
        quantity = quantity,
        ltp = ltp,
        avgPrice = avgPrice,
        close = close
    )

fun Holding.toEntity(): HoldingEntity =
    HoldingEntity(
        symbol = symbol,
        quantity = quantity,
        ltp = ltp,
        avgPrice = avgPrice,
        close = close
    )
