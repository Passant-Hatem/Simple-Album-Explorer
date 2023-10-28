package com.example.simplealbumexplorer.modules.album.di

import com.example.simplealbumexplorer.modules.album.data.AlbumRemoteService
import com.example.simplealbumexplorer.modules.album.data.DefaultAlbumRepository
import com.example.simplealbumexplorer.modules.album.domain.AlbumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object AlbumModule {

    @Provides
    @ViewModelScoped
    fun provideAlbumRemoteService(retrofit: Retrofit): AlbumRemoteService = retrofit.create(AlbumRemoteService::class.java)


    @Provides
    @ViewModelScoped
    fun provideAlbumRepository(albumRemoteService: AlbumRemoteService): AlbumRepository = DefaultAlbumRepository(albumRemoteService)

}