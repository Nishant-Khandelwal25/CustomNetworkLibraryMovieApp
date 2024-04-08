package com.example.customnetworkapimovie.di

import com.example.customnetworkapimovie.repository.MainRepository
import com.example.customnetworkapimovie.repository.MainRepositoryImpl
import com.example.customnetworkapimovie.util.NetworkHelper
import com.example.customnetworkapimovie.util.NetworkHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNetworkHelper(networkHelperImpl: NetworkHelperImpl): NetworkHelper =
        networkHelperImpl

    @Provides
    @Singleton
    fun provideRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository =
        mainRepositoryImpl
}