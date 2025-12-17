package com.example.stockportfolio.presentation.portfolio


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockportfolio.domain.usecase.CalculatePortfolioSummaryUseCase
import com.example.stockportfolio.domain.usecase.GetHoldingsUseCase
import com.example.stockportfolio.presentation.portfolio.state.PortfolioUiState

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class PortfolioViewModel(
    private val getHoldingsUseCase: GetHoldingsUseCase,
    private val calculateSummaryUseCase: CalculatePortfolioSummaryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PortfolioUiState(isLoading = true))
    val uiState: StateFlow<PortfolioUiState> = _uiState

    init {
        loadHoldings()
    }

    fun loadHoldings() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            getHoldingsUseCase()
                .collect { result ->
                    result
                        .onSuccess { holdings ->
                            val summary = calculateSummaryUseCase(holdings)
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    holdings = holdings,
                                    summary = summary,
                                    errorMessage = null
                                )
                            }
                        }
                        .onFailure { error ->
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = error.localizedMessage ?: "Unknown error"
                                )
                            }
                        }
                }
        }
    }

    fun toggleSummaryExpanded() {
        _uiState.update {
            it.copy(isSummaryExpanded = !it.isSummaryExpanded)
        }
    }
}

