package com.senaisp.carteirinhadigital.core.network

import com.senaisp.carteirinhadigital.core.auth.AuthTokenStore
import com.senaisp.carteirinhadigital.core.auth.SessionTokenStore
import okhttp3.Response
import okhttp3.Interceptor

class AuthInterceptor(
    private val sessionTokenStore: SessionTokenStore
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val requestOriginal = chain.request()
        val token = sessionTokenStore.obter()

        if (token.isNullOrBlank()) {
            return chain.proceed(requestOriginal)
        }

        val requestAutenticada = requestOriginal.newBuilder()
            .header(
                "Authorization",
                "Bearer $token"
            ).build()

        return chain.proceed(requestAutenticada)
    }
}