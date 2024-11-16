package com.paymob.data.converter.mapper

import com.paymob.data.converter.model.converter.ConverterResponse
import com.paymob.data.converter.model.symbols.SymbolsResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class ConverterMapperTest {

    private lateinit var converterMapper: ConverterMapper

    @Mock
    private lateinit var converterResponse: ConverterResponse

    @Mock
    private lateinit var symbolsResponse: SymbolsResponse

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        converterMapper = ConverterMapper()
    }

    @Test
    fun `test mapConverterResponse should return correct float result`() {
        // Arrange
        `when`(converterResponse.result).thenReturn(123.45)

        // Act
        val result = converterMapper.mapConverterResponse(converterResponse)

        // Assert
        assertEquals(123.45f, result, 0.001f)
    }

    @Test
    fun `test mapSymbolsResponse should return list of keys`() {
        // Arrange
        val mockSymbols = mapOf("USD" to "Dollar", "EUR" to "Euro")
        `when`(symbolsResponse.symbols).thenReturn(mockSymbols)

        // Act
        val result = converterMapper.mapSymbolsResponse(symbolsResponse)

        // Assert
        assertEquals(listOf("USD", "EUR"), result)
    }

    @Test
    fun `test calculateConversionRate should return correct conversion rate`() {
        // Arrange
        val rates = mapOf("USD" to 1.0, "EUR" to 0.85)
        val fromCurrency = "USD"
        val toCurrency = "EUR"
        val amount = 100f

        // Act
        val result = converterMapper.calculateConversionRate(rates, fromCurrency, toCurrency, amount)

        // Assert
        assertEquals(85.0, result, 0.001)
    }
}
