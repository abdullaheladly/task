package com.paymob.currencytask.di



import com.paymob.data.converter.remote.ConverterApi
import com.paymob.data.history.remote.HistoryRateApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ConverterApi {
        return retrofit.create(
            ConverterApi::class.java
        )
    }

    @Singleton
    @Provides
    fun provideHistoryRateApiService(retrofit: Retrofit): HistoryRateApi {
        return retrofit.create(
            HistoryRateApi::class.java
        )
    }
}
