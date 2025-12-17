package com.example.stockportfolio.presentation.portfolio.components

import androidx.compose.runtime.Composable

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stockportfolio.domain.model.PortfolioSummary
import java.text.NumberFormat
import java.util.Locale

@Composable
fun PortfolioSummaryCard(
    modifier: Modifier = Modifier,
    summary: PortfolioSummary,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit
) {
    val currencyFormatter = rememberCurrencyFormatter()

    Card(
        modifier = modifier.fillMaxWidth()
            .animateContentSize(),
        onClick = onToggleExpand
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Portfolio Summary",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = onToggleExpand) {
                    Icon(
                        imageVector = if (isExpanded) {
                            Icons.Default.KeyboardArrowDown
                        } else
                            Icons.Default.KeyboardArrowUp,
                        contentDescription = if (isExpanded) "Collapse" else "Expand"
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            SummaryRow(
                label = "Total P&L",
                value = currencyFormatter(summary.totalPnl),
                valueColor = if (summary.totalPnl >= 0) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.error
                }
            )

            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))

                SummaryRow(
                    label = "Total Investment",
                    value = currencyFormatter(summary.totalInvestment)
                )

                SummaryRow(
                    label = "Current Value",
                    value = currencyFormatter(summary.currentValue)
                )


                SummaryRow(
                    label = "Today's P&L",
                    value = currencyFormatter(summary.todayPnl),
                    valueColor = if (summary.todayPnl >= 0) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.error
                    }
                )


            }
        }
    }
}

@Composable
private fun SummaryRow(
    label: String,
    value: String,
    valueColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurface
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = valueColor
            )
        )
    }
}

@Composable
fun rememberCurrencyFormatter(): (Double) -> String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
    return { value ->
        formatter.format(value)
    }
}
