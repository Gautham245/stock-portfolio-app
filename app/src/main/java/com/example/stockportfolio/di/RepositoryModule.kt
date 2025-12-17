package com.example.stockportfolio.di

import com.example.stockportfolio.data.repository.HoldingsRepositoryImpl
import com.example.stockportfolio.domain.repository.HoldingsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<HoldingsRepository> { HoldingsRepositoryImpl(get()) }
}
