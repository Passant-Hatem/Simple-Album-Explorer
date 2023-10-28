package com.example.simplealbumexplorer.modules.album.data

import com.example.simplealbumexplorer.modules.album.data.model.toDomain
import com.example.simplealbumexplorer.modules.album.domain.AlbumRepository
import com.example.simplealbumexplorer.modules.album.domain.model.Photo
import com.example.simplealbumexplorer.modules.core.mics.emitFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultAlbumRepository @Inject constructor(
    private val remoteService: AlbumRemoteService
): AlbumRepository {

    override fun getAlbumDetails(albumId: String): Flow<List<Photo>> = emitFlow {
        remoteService.getAlbumsDetails(albumId).map { it.toDomain() }
    }
}