package com.anil.di.domain.item

import com.anil.di.remote.model.reviews.AuthorDetails
import com.anil.di.remote.model.reviews.Result
import com.google.gson.annotations.SerializedName

data class Reviews(
    @SerializedName("author")
    val author: String,
    @SerializedName("author_details")
    val authorDetails: AuthorDetails,
    @SerializedName("content")
    val content: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("url")
    val url: String
)

fun Result.toReviews() = Reviews(author, authorDetails, content, createdAt, id, updatedAt, url)