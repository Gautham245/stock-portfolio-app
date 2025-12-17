package com.example.stockportfolio.di

import com.example.stockportfolio.domain.usecase.CalculatePortfolioSummaryUseCase
import com.example.stockportfolio.domain.usecase.GetHoldingsUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetHoldingsUseCase(get()) }

    factory { CalculatePortfolioSummaryUseCase() }
}
