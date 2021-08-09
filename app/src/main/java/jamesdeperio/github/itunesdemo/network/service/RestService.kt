package jamesdeperio.github.itunesdemo.network.service

import io.reactivex.Observable
import jamesdeperio.github.itunesdemo.BuildConfig
import jamesdeperio.github.itunesdemo.model.ItunesSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RestService {
    @GET(BuildConfig.PATH_SEARCH)
    fun getItunesSearch(
        @Query("term") term:String,
        @Query("country") country:String,
        @Query("media") media:String):Observable<ItunesSearchResponse>
}