package com.sirivr.foods.di

import com.sirivr.foods.repository.BaseRepository
import com.sirivr.foods.repositoryImpl.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppRepositoryModule {

    @Provides
    @Singleton
    fun provideBaseRepository(apiServices: ApiServices): BaseRepository {
        return LoginRepositoryImpl(apiServices)
    }
}