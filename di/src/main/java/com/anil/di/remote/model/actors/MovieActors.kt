package com.anil.di.remote.model.actors


import com.google.gson.annotations.SerializedName
import android.os.Parcelable

data class MovieActors(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int
)