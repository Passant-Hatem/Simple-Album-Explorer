package com.example.simplealbumexplorer.modules.user.data.model

import com.example.simplealbumexplorer.modules.user.domain.model.User
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)

fun UserResponse.toDomain(): User =
    User(
        id = id,
        name = name,
        address = address.toString()
    )


@Serializable
data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
){
    override fun toString(): String {
        return "$suite, $street, $city, $zipcode"
    }
}

@Serializable
data class Geo(
    val lat: String,
    val lng: String
)

@Serializable
data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
)

