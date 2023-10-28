package com.example.simplealbumexplorer.modules.album.domain

import com.example.simplealbumexplorer.modules.album.domain.model.Photo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumDetails @Inject constructor(
    private val repository: AlbumRepository
) {
    operator fun invoke(albumId: String): Flow<List<Photo>> =
        repository.getAlbumDetails(albumId)
}