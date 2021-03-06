package jamesdeperio.github.itunesdemo.base.retrofit

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


abstract class RetrofitManager(private val context: Context) : RetrofitConfiguration {
    @field:[Volatile] private var retrofit: Retrofit? = null
    override fun clearRetrofit() {
        retrofit=null
    }
    override fun OkHttpClient.Builder.interceptorConfiguration(builder: OkHttpClient.Builder): OkHttpClient.Builder
            = builder

    override fun build() {
        val  okHttpClientBuilder= OkHttpClient.Builder()
        okHttpClientBuilder
                .writeTimeout(initWriteTimeOut(), TimeUnit.SECONDS)
                .connectTimeout(initConnectTimeOut(), TimeUnit.SECONDS)
                .readTimeout(initReadTimeOut(), TimeUnit.SECONDS)

        if (isPrintLogEnabled()) { // debug mode
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(logging)
        }
        okHttpClientBuilder.interceptorConfiguration(okHttpClientBuilder)
        val callAdapter = initCallAdapterFactory()
        retrofit = if (callAdapter == null)
            Retrofit.Builder()
                .baseUrl(initBaseURL())
                .client(okHttpClientBuilder.build())
                .addConverterFactory(initConverterFactory())
                .build()
        else Retrofit.Builder()
                .baseUrl(initBaseURL())
                .client(okHttpClientBuilder.build())
                .addConverterFactory(initConverterFactory())
                .addCallAdapterFactory(callAdapter)
                .build()
    }

    override fun create(service: Class<*>): Any = if (retrofit == null) synchronized(service){
        if (retrofit == null) {
            build()
        }
        return retrofit!!.create(service)
    }else retrofit!!.create(service)

    override fun isPrintLogEnabled(): Boolean = false

    override fun initCallAdapterFactory(): CallAdapter.Factory? = null
}