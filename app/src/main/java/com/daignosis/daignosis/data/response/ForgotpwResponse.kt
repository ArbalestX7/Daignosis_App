package com.daignosis.daignosis.data.response

import com.google.gson.annotations.SerializedName

data class ForgotpwResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("messages")
	val messages: String
)
