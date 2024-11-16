package com.paymob.currencytask.di




import com.paymob.data.converter.datasource.ConverterDataSource
import com.paymob.data.converter.datasource.ConverterRemoteDataSource
import com.paymob.data.converter.datasource.CurrenciesDataSource
import com.paymob.data.converter.datasource.CurrenciesLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun provideConverterDataSource(impl: ConverterRemoteDataSource): ConverterDataSource

    @Binds
    abstract fun provideCurrenciesDataSource(impl: CurrenciesLocalDataSource): CurrenciesDataSource

}
