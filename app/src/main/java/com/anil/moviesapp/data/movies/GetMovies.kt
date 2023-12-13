package com.anil.moviesapp.data.movies

import android.os.Parcel
import android.os.Parcelable

data class GetMovies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.createTypedArrayList(Result) ?: emptyList(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(page)
        parcel.writeTypedList(results)
        parcel.writeInt(total_pages)
        parcel.writeInt(total_results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GetMovies> {
        override fun createFromParcel(parcel: Parcel): GetMovies {
            return GetMovies(parcel)
        }

        override fun newArray(size: Int): Array<GetMovies?> {
            return arrayOfNulls(size)
        }
    }
}
