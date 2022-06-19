package com.example.teesas.di

import com.example.teesas.BuildConfig
import com.example.teesas.api.ApiInstance
import com.example.teesas.util.constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.google.gson.GsonBuilder

import com.google.gson.Gson


//dagger-hilt dependency injection - singleton methods
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //api url
    @Singleton
    @Provides
    fun provide_base_url() = constants.BASE_URL

    //http client and http logging interceptor initiation
    @Singleton
    @Provides
    fun provide_http_client() = if (BuildConfig.DEBUG) {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .build()
    } else {
        OkHttpClient.Builder().build()
    }

    //gson - JSON converter to data class
    var gson: Gson = GsonBuilder()
        .setLenient()
        .serializeNulls()
        .excludeFieldsWithoutExposeAnnotation()
        .create()


    //retrofit instance
    @Singleton
    @Provides
    fun retrofit_instace(okHttpClient: OkHttpClient, base_url: String): ApiInstance =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(base_url)
            .client(okHttpClient)
            .build()
            .create(ApiInstance::class.java)

}
