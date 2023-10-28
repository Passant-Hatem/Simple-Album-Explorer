package com.example.simplealbumexplorer.modules.user.di

import com.example.simplealbumexplorer.modules.user.data.DefaultUserRepository
import com.example.simplealbumexplorer.modules.user.data.UserRemoteService
import com.example.simplealbumexplorer.modules.user.domain.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit


@Module
@InstallIn(ViewModelComponent::class)
object UserModule {

    @Provides
    @ViewModelScoped
    fun provideUserRemoteService(retrofit: Retrofit): UserRemoteService = retrofit.create(UserRemoteService::class.java)

    @Provides
    @ViewModelScoped
    fun provideUserRepository(userRemoteService: UserRemoteService): UserRepository = DefaultUserRepository(userRemoteService)

}