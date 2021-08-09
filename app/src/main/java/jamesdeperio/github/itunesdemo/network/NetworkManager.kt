package jamesdeperio.github.itunesdemo.network

import android.content.Context
import com.google.gson.GsonBuilder
import jamesdeperio.github.itunesdemo.BuildConfig
import jamesdeperio.github.itunesdemo.base.retrofit.RetrofitManager
import jamesdeperio.github.itunesdemo.util.NetworkUtil
import jamesdeperio.github.itunesdemo.network.service.RestService
import jamesdeperio.github.itunesdemo.network.repository.RestRepository
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManager(context: Context) : RetrofitManager(context) {
    //region NETWORK CONFIGURATION
    override fun initBaseURL(): String = BuildConfig.BASE_URL


    override fun initConverterFactory(): Converter.Factory
    = GsonConverterFactory.create(GsonBuilder().setLenient().create())


    override fun initCallAdapterFactory(): CallAdapter.Factory
    = RxJava2CallAdapterFactory.create()

    override fun isPrintLogEnabled(): Boolean
    = true
    //endregion
}
