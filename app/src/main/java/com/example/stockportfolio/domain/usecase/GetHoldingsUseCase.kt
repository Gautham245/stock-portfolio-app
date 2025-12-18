package com.example.stockportfolio.domain.usecase

import com.example.stockportfolio.domain.model.Holding
import com.example.stockportfolio.domain.repository.HoldingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetHoldingsUseCase(
    private val repository: HoldingsRepository
) {
    operator fun invoke(): Flow<Result<List<Holding>>> {
        return repository.getHoldings()
    }
}

