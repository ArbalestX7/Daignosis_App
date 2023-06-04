package com.daignosis.daignosis.data.response

import com.google.gson.annotations.SerializedName

data class ForgotResponse(

	@field:SerializedName("messages")
	val messages: String,

	@field:SerializedName("error")
	val error: Boolean
)
