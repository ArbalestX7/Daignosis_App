package com.daignosis.daignosis.data.response

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

	@field:SerializedName("data")
	val dataHistory: List<DataHistory>,

	@field:SerializedName("error")
	val error: Boolean
)

data class DataHistory(

	@field:SerializedName("session_id")
	val sessionId: String,

	@field:SerializedName("latest_chat_date")
	val latestChatDate: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("is_bot")
	val isBot: Int
)
