package com.example.navigation_multi_module_project.di

import com.example.navigation.NavProvider
import com.kursatkumsuz.detail.DetailNavRegisterer
import com.kursatkumsuz.home.HomeNavRegisterer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NavModule {

    @Provides
    @Singleton
    fun provideNavProvider() : NavProvider = NavProvider(
        homeScreen = HomeNavRegisterer(),
        detailScreen = DetailNavRegisterer()
    )

}