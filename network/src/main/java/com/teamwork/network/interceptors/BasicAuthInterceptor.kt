package com.teamwork.network.interceptors

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor(username: String) : Interceptor {

    private val credentials: String = Credentials.basic(username,
        PASSWORD
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
            .header("Authorization", credentials).build()
        return chain.proceed(authenticatedRequest)
    }

    companion object {
        private const val PASSWORD = "X"
    }
}