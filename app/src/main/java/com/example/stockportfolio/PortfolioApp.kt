package com.example.stockportfolio

import android.app.Application
import com.example.stockportfolio.di.networkModule
import com.example.stockportfolio.di.repositoryModule
import com.example.stockportfolio.di.useCaseModule
import com.example.stockportfolio.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class PortfolioApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PortfolioApp)
            modules(
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}