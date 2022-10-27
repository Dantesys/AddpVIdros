package com.dantesys.addpvidros.data.services

import android.content.Context
import com.dantesys.addpvidros.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthIntecerptors(context: Context) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // If token has been saved, add it to the request
        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}