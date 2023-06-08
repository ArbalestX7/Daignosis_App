package com.daignosis.daignosis.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class Data(

	@field:SerializedName("birthday")
	val birthday: String,

	@field:SerializedName("full_name")
	val fullName: String,

	@field:SerializedName("user_id")
	val userId: String,

	@field:SerializedName("photo_profile")
	val photoProfile: String,

	@field:SerializedName("phone_number")
	val phoneNumber: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("token")
	val token: String
)
