package com.example.simplealbumexplorer.modules.user.data.model

import com.example.simplealbumexplorer.modules.user.domain.model.Album
import kotlinx.serialization.Serializable

@Serializable
data class AlbumResponse(
    val id: Int,
    val title: String,
    val userId: Int
)

fun AlbumResponse.toDomain(): Album =
    Album(
        id = id,
        title = title
    )