package com.example.composition.inject

import com.example.composition.navigation.NavigationHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNavigationHandler(): NavigationHandler {
        return NavigationHandler()
    }
}