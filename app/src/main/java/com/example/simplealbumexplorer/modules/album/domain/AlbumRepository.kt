package com.example.simplealbumexplorer.modules.album.domain

import com.example.simplealbumexplorer.modules.album.domain.model.Photo
import kotlinx.coroutines.flow.Flow


interface AlbumRepository {

    fun getAlbumDetails(albumId: String): Flow<List<Photo>>

}