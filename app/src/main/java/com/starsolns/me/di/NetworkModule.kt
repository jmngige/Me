package com.starsolns.me.di

import android.content.Context
import com.starsolns.me.data.network.NetworkApi
import com.starsolns.me.data.network.OauthInterceptor
import com.starsolns.me.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideOkHttp(
        @ApplicationContext context: Context
    ): OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(OauthInterceptor(context))
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideGSonConverter(): GsonConverterFactory{
        return GsonConverterFactory.create()
    }

}