package com.senaisp.carteirinhadigital.feature.login.data.repository

import com.senaisp.carteirinhadigital.core.auth.SessionTokenStore
import com.senaisp.carteirinhadigital.core.network.NetworkClient
import com.senaisp.carteirinhadigital.feature.login.data.remote.service.AuthApi

object LoginRepositoryProvider {
    private const val USE_FAKE_REPOSITORY = false

    fun provide(): LoginRepository {
        return if (USE_FAKE_REPOSITORY) {
            FakeLoginRepositoryImpl()
        } else {
            val networkClient = NetworkClient(
                baseUrl = "http://10.0.2.2:8080/",
                sessionTokenStore = SessionTokenStore()
            )
            val authApi = networkClient.createPublic(AuthApi::class.java)
            ApiLoginRepositoryImpl(api = authApi)
        }
    }
}
