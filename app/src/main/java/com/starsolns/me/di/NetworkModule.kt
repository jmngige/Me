package com.starsolns.me.di

import com.starsolns.me.data.network.NetworkApi
import com.starsolns.me.data.network.OauthInterceptor
import com.starsolns.me.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): NetworkApi{
        return retrofit.create(NetworkApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(OauthInterceptor("Bearer", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYyMTNlNTBjZGI5ZmEwNDZmZmU5NWNhMyIsImlhdCI6MTY1NTExMzY5MSwiZXhwIjoxNjU1NzE4NDkxfQ.Uo-mZ_Y6vwE1td3G0WXH9Qkx92aHtlSDZBRfT-TWK10"))
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideGSonConverter(): GsonConverterFactory{
        return GsonConverterFactory.create()
    }

}