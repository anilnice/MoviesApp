package com.anil.moviesapp.data.actors


import com.google.gson.annotations.SerializedName

data class MovieActors(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int
)