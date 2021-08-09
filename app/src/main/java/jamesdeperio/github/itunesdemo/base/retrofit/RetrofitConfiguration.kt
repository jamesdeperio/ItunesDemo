package jamesdeperio.github.itunesdemo.base.retrofit
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter


internal interface RetrofitConfiguration {
    fun initBaseURL(): String
    fun initWriteTimeOut(): Long = 10
    fun initConnectTimeOut(): Long = 10
    fun initReadTimeOut(): Long = 30
    fun create(service: Class<*>): Any
    fun initConverterFactory(): Converter.Factory
    fun initCallAdapterFactory(): CallAdapter.Factory?
    fun isPrintLogEnabled(): Boolean
    fun build()
    fun OkHttpClient.Builder.interceptorConfiguration(builder: OkHttpClient.Builder): OkHttpClient.Builder
    fun clearRetrofit()
}