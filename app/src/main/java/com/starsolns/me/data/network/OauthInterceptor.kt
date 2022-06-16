package com.starsolns.me.data.network

import android.content.Context
import android.content.SharedPreferences
import com.starsolns.me.data.datastore.SessionManager
import com.starsolns.me.util.Settings
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response


class OauthInterceptor(
    val context: Context
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val pref: SharedPreferences = context.getSharedPreferences("me_prefs", Context.MODE_PRIVATE)
        val settings = Settings(pref)

        var request = chain.request()
            .newBuilder()
            .header("Authorization", "Bearer ${settings.getBearerToken()}")
            .build()

        return chain.proceed(request)
    }
}