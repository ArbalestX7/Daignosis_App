package com.daignosis.daignosis.data.response

import com.google.gson.annotations.SerializedName

data class MessageResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("error")
	val error: Boolean
)

data class Data(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("accuracy")
	val accuracy: Int,

	@field:SerializedName("clinic")
	val clinic: List<Any>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("out")
	val out: String
)
