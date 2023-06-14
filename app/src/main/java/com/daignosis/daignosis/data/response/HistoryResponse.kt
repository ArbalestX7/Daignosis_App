package com.daignosis.daignosis.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class HistoryResponse(

	@field:SerializedName("data")
	val dataHistory: List<DataHistory>,

	@field:SerializedName("error")
	val error: Boolean
)

@Parcelize
data class DataHistory(

	@field:SerializedName("session_id")
	val sessionId: String,

	@field:SerializedName("latest_chat_date")
	val latestChatDate: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("is_bot")
	val isBot: Int
): Parcelable
