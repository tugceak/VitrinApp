package com.tugceaksoy.vitrinapp.di


import com.tugceaksoy.vitrinapp.data.repository.VitrinRepository
import com.tugceaksoy.vitrinapp.data.repository.VitrinRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    internal abstract fun bindRocketsRepository(repository: VitrinRepositoryImpl) :VitrinRepository
}