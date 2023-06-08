package com.daignosis.daignosis.data.entity

data class UserEntity(
    val userId: String,
    val name: String,
    val email: String,
    val password: String,
    val token: String,
)