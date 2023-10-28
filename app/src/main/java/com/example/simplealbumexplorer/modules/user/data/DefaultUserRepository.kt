package com.example.simplealbumexplorer.modules.user.data

import com.example.simplealbumexplorer.modules.core.mics.emitFlow
import com.example.simplealbumexplorer.modules.user.data.model.toDomain
import com.example.simplealbumexplorer.modules.user.domain.model.User
import com.example.simplealbumexplorer.modules.user.domain.UserRepository
import com.example.simplealbumexplorer.modules.user.domain.model.Album
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val remoteService: UserRemoteService
) : UserRepository{

    override fun getAllUsers(): Flow<List<User>> = emitFlow {
        remoteService.getAllUsers().map { it.toDomain() }
    }

    override fun getUserAlbums(userId: String): Flow<List<Album>> = emitFlow {
        remoteService.getUserAlbums(userId).map { it.toDomain() }
    }
}