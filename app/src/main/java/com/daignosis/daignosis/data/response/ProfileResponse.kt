package com.daignosis.daignosis.data.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("dataUser")
	val dataUser: DataUser,

	@field:SerializedName("error")
	val error: Boolean
)

data class DataUser(

	@field:SerializedName("birthday")
	val birthday: Any,

	@field:SerializedName("country")
	val country: String,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("photo_profile")
	val photoProfile: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("address_id")
	val addressId: String,

	@field:SerializedName("creation_date")
	val creationDate: String,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("full_name")
	val fullName: Any,

	@field:SerializedName("province")
	val province: String,

	@field:SerializedName("user_id")
	val userId: String,

	@field:SerializedName("phone_number")
	val phoneNumber: Any,

	@field:SerializedName("postal_code")
	val postalCode: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
