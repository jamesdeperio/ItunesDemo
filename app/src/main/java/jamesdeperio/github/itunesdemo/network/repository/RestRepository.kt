package jamesdeperio.github.itunesdemo.network.repository


import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import jamesdeperio.github.itunesdemo.BuildConfig
import jamesdeperio.github.itunesdemo.feature.home.itunes.list.ItunesContent
import jamesdeperio.github.itunesdemo.model.ItunesSearchResponse
import jamesdeperio.github.itunesdemo.network.dao.ApiCacheEntity
import jamesdeperio.github.itunesdemo.network.dao.AppDatabase
import jamesdeperio.github.itunesdemo.network.service.RestService


class RestRepository(
    private val restService: RestService,
    private val database: AppDatabase) {

    fun getItunesSearchResponse(term:String, country:String, media:String) = restService.getItunesSearch(
        term =term, country = country, media = media)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.newThread())
        .doOnNext {
            database.apiCacheDao().insertCache(apiCacheEntity =
            ApiCacheEntity(key = BuildConfig.PATH_SEARCH, cache = Gson().toJson(it)))
        }
        .map {
            val contentList:MutableList<ItunesContent> = ArrayList()
            contentList.addAll(sortedContents(it))

            return@map contentList
        }!!

    fun getItunesSearchCache() = Observable.fromCallable {
        database.apiCacheDao().findCacheByKey(BuildConfig.PATH_SEARCH)
    }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.newThread())
        .map {
            val contentList:MutableList<ItunesContent> = ArrayList()
            val response:ItunesSearchResponse = Gson().fromJson(it.cache,ItunesSearchResponse::class.java)
            contentList.addAll(sortedContents(response))

            return@map contentList
        }!!

    /*
     MAP DOMAIN MODEL to UI MODEL
     */
    private fun sortedContents(response: ItunesSearchResponse): Collection<ItunesContent> {
        val contentList:MutableList<ItunesContent> = ArrayList()
        response.itunesData
            .sortedBy { data->
                data.primaryGenreName
            }
            .forEach { data->
                if (contentList.size==0 ||(contentList.size!=0 && contentList.last().data?.primaryGenreName != data.primaryGenreName)){
                    contentList.add(
                        ItunesContent(
                            genre = data.primaryGenreName?:"Others",
                            isHeader = true))
                }
                contentList.add(
                    ItunesContent(
                        id = data.trackId,
                        genre = data.primaryGenreName?:"Others",
                        data = data
                    ))
            }

        return contentList
    }

}