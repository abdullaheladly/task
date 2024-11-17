package com.paymob.presentation.converter

import com.paymob.presentation.base.BaseComposableScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.paymob.presentation.HistoryInput
import com.paymob.presentation.NavRoutes


@Composable
fun ConverterScreen(modifier: Modifier = Modifier,navController: NavController) {
    val convertViewModel = hiltViewModel<ConverterViewModel>()
    val currenciesList = convertViewModel.currenciesDataStateFlow.collectAsState()
    val result = convertViewModel.resultStateFlow.collectAsState()

    var amountState by remember {
        mutableStateOf(0f)
    }

    BaseComposableScreen(viewModel = convertViewModel) {
        CurrencyConverterScreen(currencies = currenciesList.value,
            amount = amountState.toString(),
            convertedValue = result.value.toString(),
            onFromCurrencyChange = {
                convertViewModel.changeFromCurrency(it)
            },
            onToCurrencyChange = {
                convertViewModel.changeToCurrency(it)
            },
            onAmountChange = {
                amountState = it.toFloat()
            },
            onSwapCurrencies = {
                convertViewModel.swap()
            },
            onConvert = {
                convertViewModel.convert(amountState)
            }, onHistoryClicked = {
                navController.navigate(NavRoutes.History.routeForHistory(HistoryInput(convertViewModel.fromCurrencyStateFlow.value,convertViewModel.toCurrencyStateFlow.value)))
            })
    }


}

@Composable
fun CurrencyConverterScreen(
    currencies: List<String>,
    amount: String,
    convertedValue: String,
    onFromCurrencyChange: (String) -> Unit,
    onToCurrencyChange: (String) -> Unit,
    onAmountChange: (String) -> Unit,
    onSwapCurrencies: () -> Unit,
    onConvert: () -> Unit,
    onHistoryClicked: () -> Unit,
) {
    var isFromDropdownOpen by remember { mutableStateOf(false) }
    var isToDropdownOpen by remember { mutableStateOf(false) }
    var selectedFromCurrency by remember { mutableStateOf("") }
    var selectedToCurrency by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // From Currency Dropdown
        Text("From Currency")
        Box {
            Text(
                text = if (selectedFromCurrency.isEmpty()) "Select a currency" else selectedFromCurrency,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isFromDropdownOpen = true }
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                    .padding(16.dp)
            )
            DropdownMenu(
                expanded = isFromDropdownOpen,
                onDismissRequest = { isFromDropdownOpen = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                currencies.forEach { currency ->
                    DropdownMenuItem(
                        onClick = {
                            isFromDropdownOpen = false
                            selectedFromCurrency = currency
                            onFromCurrencyChange(currency)
                        },
                        text = { Text(currency) }
                    )
                }
            }
        }

        // To Currency Dropdown
        Text("To Currency")
        Box {
            Text(
                text = if (selectedToCurrency.isEmpty()) "Select a currency" else selectedToCurrency,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isToDropdownOpen = true }
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                    .padding(16.dp)
            )
            DropdownMenu(
                expanded = isToDropdownOpen,
                onDismissRequest = { isToDropdownOpen = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                currencies.forEach { currency ->
                    DropdownMenuItem(
                        onClick = {
                            isToDropdownOpen = false
                            selectedToCurrency = currency
                            onToCurrencyChange(currency)
                        },
                        text = { Text(currency) }
                    )
                }
            }
        }

        // Amount Input Field
        Text("Amount")
        BasicTextField(
            value = amount,
            onValueChange = onAmountChange,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Converted Value Field (Readonly)
        Text("Converted Value")
        Text(
            text = convertedValue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            style = MaterialTheme.typography.headlineLarge
        )

        // Swap Button
        Button(
            onClick = {
                selectedToCurrency = selectedFromCurrency.also { selectedFromCurrency = selectedToCurrency }
                onSwapCurrencies()
            }, modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Swap")
        }
        // Convert Button
        Button(
            onClick = onConvert,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        ) {
            Text("Convert")
        }

        Button(
            onClick = onHistoryClicked,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        ) {
            Text("History")
        }
    }
}

