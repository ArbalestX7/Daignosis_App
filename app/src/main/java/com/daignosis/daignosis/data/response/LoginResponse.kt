package com.daignosis.daignosis.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val loginResult: LoginResult,

	@field:SerializedName("message")
	val message: String
)

data class LoginResult(

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
