package com.paymob.currencytask.di


import com.paymob.data.converter.repo.ConverterRepoImpl
import com.paymob.domain.converter.repo.ConverterRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun provideUsersRepository(impl: ConverterRepoImpl): ConverterRepo

}
