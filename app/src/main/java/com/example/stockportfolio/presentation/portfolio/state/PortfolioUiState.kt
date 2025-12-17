package com.example.stockportfolio.presentation.portfolio.state

import com.example.stockportfolio.domain.model.Holding
import com.example.stockportfolio.domain.model.PortfolioSummary


data class PortfolioUiState(
    val isLoading: Boolean = false,
    val holdings: List<Holding> = emptyList(),
    val summary: PortfolioSummary? = null,
    val isSummaryExpanded: Boolean = false,
    val errorMessage: String? = null
)
