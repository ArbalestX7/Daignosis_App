package com.daignosis.daignosis.data.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("data")
	val registerResult: RegisterResult,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("token")
	val token: String
)

data class RegisterResult(

	@field:SerializedName("birthday")
	val birthday: Any,

	@field:SerializedName("full_name")
	val fullName: Any,

	@field:SerializedName("photo_profile")
	val photoProfile: Any,

	@field:SerializedName("phone_number")
	val phoneNumber: Any,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
