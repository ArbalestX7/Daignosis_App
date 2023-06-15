package com.daignosis.daignosis.data.response

import com.google.gson.annotations.SerializedName

data class HistoryMsgResponse(

	@field:SerializedName("data")
	val data: List<DataItemMsg>,

	@field:SerializedName("error")
	val error: Boolean
)

data class DataItemMsg(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("accuracy")
	val accuracy: Any,

	@field:SerializedName("is_bot")
	val isBot: Int,

	@field:SerializedName("message")
	val message: String
)
