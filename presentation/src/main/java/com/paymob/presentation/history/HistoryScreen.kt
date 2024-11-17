package com.paymob.presentation.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.paymob.presentation.HistoryInput
import com.paymob.presentation.R
import com.paymob.presentation.base.BaseComposableScreen


@Composable
fun HistoryScreen(modifier: Modifier = Modifier,historyInput: HistoryInput) {
    val viewModel = hiltViewModel<HistoryViewModel>()
    BaseComposableScreen(viewModel = viewModel) {
        HistoryScreenView(viewModel, historyInput.baseCurrency, historyInput.targetCurrency)
    }
}

@Composable
fun HistoryScreenView(
    viewModel: HistoryViewModel,
    base: String,
    target: String
) {


    LaunchedEffect(Unit) {
        viewModel.getHistoryRates(
            target = viewModel.getTargetCurrencies(target),
            endDate = viewModel.getDate(),
            startDate = viewModel.getDate(3),
            base = base
        )
    }

    viewModel.historyRatesFlow.collectAsState().value.let { historyRateListModel ->
        Box(contentAlignment = Alignment.Center) {
            Row {

                LazyColumn(
                    modifier = Modifier
                        .weight(1.0f)
                        .fillMaxWidth()
                ) {
                    items(viewModel.filterRates(historyRateListModel.items, target)) { history ->
                        HistoryItem(
                            history = history,
                            onEditClick = {

                            },
                            textColor = Color.White,
                            backgroundColor = colorResource(id = R.color.sky_blue)
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(0.1f))

                LazyColumn(
                    modifier = Modifier
                        .weight(1.0f)
                        .fillMaxWidth()
                ) {
                    items(
                        viewModel.filterOtherPopularRates(
                            historyRateListModel.items,
                            target
                        )
                    ) { history ->
                        HistoryItem(
                            history = history,
                            onEditClick = {

                            },
                            textColor = Color.Black,
                            backgroundColor = colorResource(id = R.color.sky_blue)
                        )
                    }
                }
            }

        }
    }

}