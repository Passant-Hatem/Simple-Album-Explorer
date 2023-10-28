package com.example.simplealbumexplorer.modules.album.data.model

import com.example.simplealbumexplorer.modules.album.domain.model.Photo
import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponse(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)

fun PhotoResponse.toDomain(): Photo =
    Photo(
        albumId = albumId.toString(),
        imageId = id.toString(),
        imageUrl =  url,
        title = title
    )