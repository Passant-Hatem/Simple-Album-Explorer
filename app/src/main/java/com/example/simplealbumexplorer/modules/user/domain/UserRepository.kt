package com.example.simplealbumexplorer.modules.user.domain

import com.example.simplealbumexplorer.modules.user.domain.model.Album
import com.example.simplealbumexplorer.modules.user.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getAllUsers(): Flow<List<User>>

    fun getUserAlbums(userId: String): Flow<List<Album>>

}