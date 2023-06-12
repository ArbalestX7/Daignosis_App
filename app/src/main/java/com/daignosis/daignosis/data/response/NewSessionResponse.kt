package com.daignosis.daignosis.data.response

import com.google.gson.annotations.SerializedName

data class NewSessionResponse(

	@field:SerializedName("data")
	val dataSession: DataSession,

	@field:SerializedName("error")
	val error: Boolean
)

data class DataSession(

	@field:SerializedName("session_id")
	val sessionId: String
)
