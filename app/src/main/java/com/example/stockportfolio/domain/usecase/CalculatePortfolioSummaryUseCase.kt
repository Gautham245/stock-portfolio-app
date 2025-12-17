package com.example.stockportfolio.domain.usecase

import com.example.stockportfolio.domain.model.Holding
import com.example.stockportfolio.domain.model.PortfolioSummary


class CalculatePortfolioSummaryUseCase {
    operator fun invoke(holdings: List<Holding>): PortfolioSummary {
        val currentValue = holdings.sumOf { it.currentValue }
        val totalInvestment = holdings.sumOf { it.investmentValue }
        val totalPnl = currentValue - totalInvestment
        val todayPnl = holdings.sumOf { it.todayPnl }

        return PortfolioSummary(
            currentValue = currentValue,
            totalInvestment = totalInvestment,
            totalPnl = totalPnl,
            todayPnl = todayPnl
        )
    }
}

