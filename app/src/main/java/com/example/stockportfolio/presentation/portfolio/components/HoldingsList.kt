package com.example.stockportfolio.presentation.portfolio.components

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.stockportfolio.domain.model.Holding


@Composable
fun HoldingsList(holdings: List<Holding>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(holdings) { holding ->
            HoldingItemCard(holding)
        }
        item {
            Spacer(modifier = Modifier.fillMaxWidth().height(150.dp))
        }
    }
}

@Composable
fun HoldingItemCard(holding: Holding) {
    val currencyFormatter = rememberCurrencyFormatter()
    val perStockPnl = holding.currentValue - holding.investmentValue
    val pnlColor = if (perStockPnl >= 0) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.error
    }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = holding.symbol,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Qty: ${holding.quantity}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "LTP",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = currencyFormatter(holding.ltp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Column {
                    Text(
                        text = "Avg. Price",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = currencyFormatter(holding.avgPrice),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "P&L",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = currencyFormatter(perStockPnl),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = pnlColor
                        )
                    )
                }
            }
        }
    }
}
