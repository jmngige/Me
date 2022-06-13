package com.starsolns.me.data.network

import okhttp3.Interceptor
import okhttp3.Response

class OauthInterceptor constructor(
    private val tokenType: String,
    private val accessToken: String
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
            .newBuilder()
            .header("Authorization", "$tokenType $accessToken")
            .build()

        return chain.proceed(request)
    }
}