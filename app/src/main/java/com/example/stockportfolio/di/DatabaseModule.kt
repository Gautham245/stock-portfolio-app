package com.example.stockportfolio.di


import androidx.room.Room
import com.example.stockportfolio.data.local.AppDatabase
import com.example.stockportfolio.data.local.HoldingsDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "stock_portfolio_db"
        ).build()
    }

    single<HoldingsDao> { get<AppDatabase>().holdingsDao() }
}
