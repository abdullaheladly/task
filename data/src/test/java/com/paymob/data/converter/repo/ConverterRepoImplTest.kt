package com.paymob.data.converter.repo


import com.paymob.data.converter.datasource.ConverterDataSource
import com.paymob.data.converter.datasource.CurrenciesDataSource
import com.paymob.data.converter.mapper.ConverterMapper
import com.paymob.data.converter.model.rates.ChangeCurrencyResponse
import com.paymob.data.converter.model.symbols.SymbolsResponse
import com.paymob.domain.converter.repo.ConverterRepo
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class ConverterRepoImplTest {

    @Mock
    private lateinit var converterDataSource: ConverterDataSource

    @Mock
    private lateinit var currenciesDataSource: CurrenciesDataSource

    @Mock
    private lateinit var converterMapper: ConverterMapper

    private lateinit var converterRepo: ConverterRepo

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        converterRepo = ConverterRepoImpl(converterDataSource, currenciesDataSource, converterMapper)
    }

    @Test
    fun `getALlSymbols should return mapped symbols from data source`(): Unit = runBlocking {
        // Arrange
        val mockSymbolsResponse = mock(SymbolsResponse::class.java)
        val mappedSymbols = listOf("USD", "EUR", "GBP")

        `when`(converterDataSource.getALlSymbols()).thenReturn(mockSymbolsResponse)
        `when`(converterMapper.mapSymbolsResponse(mockSymbolsResponse)).thenReturn(mappedSymbols)

        // Act
        val result = converterRepo.getALlSymbols()

        // Assert
        assertEquals(mappedSymbols, result)
        verify(converterDataSource).getALlSymbols()
        verify(converterMapper).mapSymbolsResponse(mockSymbolsResponse)
    }

    @Test
    fun `convertCurrency should calculate conversion rate from saved rates`(): Unit = runBlocking {
        // Arrange
        val mockRates = mapOf("USD" to 1.0, "EUR" to 0.85)
        val fromCurrency = "USD"
        val toCurrency = "EUR"
        val amount = 100f
        val expectedConversion = 85.0f

        `when`(currenciesDataSource.getCurrencies()).thenReturn(ChangeCurrencyResponse(rates = mockRates, base = "", date = "", success = true, timestamp = 1, error = null))
        `when`(converterMapper.calculateConversionRate(mockRates, fromCurrency, toCurrency, amount)).thenReturn(expectedConversion.toDouble())

        // Act
        val result = converterRepo.convertCurrency(fromCurrency, toCurrency, amount)

        // Assert
        assertEquals(expectedConversion, result)
        verify(currenciesDataSource).getCurrencies()
        verify(converterMapper).calculateConversionRate(mockRates, fromCurrency, toCurrency, amount)
        verify(converterDataSource, never()).convertCurrency()
    }

    @Test
    fun `convertCurrency should fetch new rates if no saved rates exist`(): Unit = runBlocking {
        // Arrange
        val mockRates = mapOf("USD" to 1.0, "EUR" to 0.85)
        val mockCurrencyResponse = ChangeCurrencyResponse(rates = mockRates, base = "", date = "", success = true, timestamp = 1, error = null)
        val fromCurrency = "USD"
        val toCurrency = "EUR"
        val amount = 100f
        val expectedConversion = 85.0f

        `when`(currenciesDataSource.getCurrencies()).thenReturn(null)
        `when`(converterDataSource.convertCurrency()).thenReturn(mockCurrencyResponse)
        `when`(converterMapper.calculateConversionRate(mockRates, fromCurrency, toCurrency, amount)).thenReturn(expectedConversion.toDouble())

        // Act
        val result = converterRepo.convertCurrency(fromCurrency, toCurrency, amount)

        // Assert
        assertEquals(expectedConversion, result)
        verify(currenciesDataSource).getCurrencies()
        verify(converterDataSource).convertCurrency()
        verify(currenciesDataSource).saveCurrencies(mockCurrencyResponse)
        verify(converterMapper).calculateConversionRate(mockRates, fromCurrency, toCurrency, amount)
    }
}
