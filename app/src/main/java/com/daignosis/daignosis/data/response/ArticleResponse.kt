package com.daignosis.daignosis.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ArticleResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("error")
	val error: Boolean
)

@Parcelize
data class DataItem(

	@field:SerializedName("article_id")
	val articleId: String,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("article_post")
	val articlePost: String,

	@field:SerializedName("article_name")
	val articleName: String,

	@field:SerializedName("photo_article")
	val photoArticle: String,

	@field:SerializedName("creation_date")
	val creationDate: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("update_date")
	val updateDate: String
): Parcelable
