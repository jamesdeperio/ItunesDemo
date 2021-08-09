package jamesdeperio.github.itunesdemo.model


import com.google.gson.annotations.SerializedName

data class ItunesSearchResponse(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val itunesData: List<ItunesData>
)