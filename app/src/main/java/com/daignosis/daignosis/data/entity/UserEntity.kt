package com.daignosis.daignosis.data.entity

data class UserEntity(
    val name: String,
    val email: String,
    val password: String,
    val isLogin: Boolean,
    val token: String,
)