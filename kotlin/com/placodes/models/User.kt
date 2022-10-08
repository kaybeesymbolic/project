package com.placodes.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class UserSession(val token: String)


@Serializable
data class UserInfo(
    val id: String,
    val name: String,
    val picture: String,
    val locale: String,
)

@Serializable
data class UserDto(
    val id:String,
    val name: String,
    val picture: String,
)


internal fun UserInfo.toDto() = UserDto(this.id, this.name, this.picture)