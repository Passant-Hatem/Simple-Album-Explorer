package com.example.simplealbumexplorer.modules.user.domain

import com.example.simplealbumexplorer.modules.user.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUsers @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<List<User>> =
        repository.getAllUsers()
}