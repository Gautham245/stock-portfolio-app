package com.example.stockportfolio.di

import com.example.stockportfolio.presentation.portfolio.PortfolioViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val viewModelModule = module {
    viewModelOf(::PortfolioViewModel)
}
