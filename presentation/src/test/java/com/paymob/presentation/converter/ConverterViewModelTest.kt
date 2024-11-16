package com.paymob.presentation.converter

import app.cash.turbine.test
import com.paymob.domain.converter.usecase.ConvertCurrencyUseCase
import com.paymob.domain.converter.usecase.GetAllSymbolsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ConverterViewModelTest {

    private val scheduler = TestCoroutineScheduler()
    private val dispatcher = StandardTestDispatcher(scheduler)
    private val testScope = TestScope(dispatcher)

    private lateinit var viewModel: ConverterViewModel

    private val mockConvertCurrencyUseCase = mockk<ConvertCurrencyUseCase>()
    private val mockGetAllSymbolsUseCase = mockk<GetAllSymbolsUseCase>()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        coEvery { mockGetAllSymbolsUseCase.invoke() } returns listOf("USD", "EUR")
        coEvery { mockConvertCurrencyUseCase.invoke(any(), any(), any()) } returns 1.0f

        viewModel = ConverterViewModel(
            convertCurrencyUseCase = mockConvertCurrencyUseCase,
            getAllSymbolsUseCase = mockGetAllSymbolsUseCase,
            defaultDispatcher = dispatcher
        )
    }

    @Test
    fun `getAllSymbols initializes currencies and sets defaults`() = testScope.runTest {
        viewModel.currenciesDataStateFlow.test {
            assertEquals(listOf("USD", "EUR"), awaitItem())
        }
        assertEquals("USD", viewModel.fromCurrencyStateFlow.value)
        assertEquals("EUR", viewModel.toCurrencyStateFlow.value)
    }

    @Test
    fun `convert updates result state flow`() = testScope.runTest {
        viewModel.convert(100f)

        viewModel.resultStateFlow.test {
            assertEquals(0.0f, awaitItem())
        }
    }

    @Test
    fun `changeFromCurrency updates fromCurrency and resets result`() = testScope.runTest {
        viewModel.changeFromCurrency("GBP")

        assertEquals("GBP", viewModel.fromCurrencyStateFlow.value)
        assertEquals(0f, viewModel.resultStateFlow.value)
    }

    @Test
    fun `changeToCurrency updates toCurrency and resets result`() = testScope.runTest {
        viewModel.changeToCurrency("AUD")

        assertEquals("AUD", viewModel.toCurrencyStateFlow.value)
        assertEquals(0f, viewModel.resultStateFlow.value)
    }

    @Test
    fun `swap exchanges fromCurrency and toCurrency and triggers conversion`() = testScope.runTest {
        viewModel.swap()

        assertEquals("EUR", viewModel.fromCurrencyStateFlow.value)
        assertEquals("USD", viewModel.toCurrencyStateFlow.value)
        assertEquals(0.0f, viewModel.resultStateFlow.value)
    }
}

