package com.daignosis.daignosis.data.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("data")
	val dataRegis: DataRegis,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class DataRegis(

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
