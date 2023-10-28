package com.example.simplealbumexplorer.modules.user.domain

import com.example.simplealbumexplorer.modules.user.domain.model.Album
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserAlbums @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(userId: String): Flow<List<Album>> =
        repository.getUserAlbums(userId)
}