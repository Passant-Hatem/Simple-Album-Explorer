package com.example.simplealbumexplorer.modules.user.data

import com.example.simplealbumexplorer.modules.user.data.model.AlbumResponse
import com.example.simplealbumexplorer.modules.user.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserRemoteService {

    @GET("users")
    suspend fun getAllUsers(): List<UserResponse>

    @GET("users/{id}/albums")
    suspend fun getUserAlbums(
        @Path("id") id: String,
    ): List<AlbumResponse>

}