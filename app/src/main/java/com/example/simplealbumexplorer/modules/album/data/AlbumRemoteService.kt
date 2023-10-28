package com.example.simplealbumexplorer.modules.album.data

import com.example.simplealbumexplorer.modules.album.data.model.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumRemoteService {

    @GET("albums/{id}/photos")
    suspend fun getAlbumsDetails(
        @Path("id") id: String,
    ): List<PhotoResponse>

}