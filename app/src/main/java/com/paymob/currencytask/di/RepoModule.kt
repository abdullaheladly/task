package com.paymob.currencytask.di


import com.paymob.data.converter.repo.ConverterRepoImpl
import com.paymob.data.history.repo.HistoryRateRepositoryImpl
import com.paymob.domain.converter.repo.ConverterRepo
import com.paymob.domain.history.repo.HistoryRateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun provideConverterRepository(impl: ConverterRepoImpl): ConverterRepo

    @Binds
    abstract fun provideHistoryRepository(impl: HistoryRateRepositoryImpl): HistoryRateRepository


}
