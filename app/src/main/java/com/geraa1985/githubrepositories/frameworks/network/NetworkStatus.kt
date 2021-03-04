package com.geraa1985.githubrepositories.frameworks.network

import com.geraa1985.githubrepositories.adapters.repository.INetworkStatus
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NetworkStatus @Inject constructor(private val myUrl: String) : INetworkStatus {

    override suspend fun isConnected(): Boolean = suspendCoroutine { continuation ->

        Thread{
            val url = URL(myUrl)
            try {
                val httpUrlConnection = url.openConnection() as HttpURLConnection
                httpUrlConnection.connectTimeout = 3000
                httpUrlConnection.connect()
                if (httpUrlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                    continuation.resume(true)
                }
            } catch (e: Exception) {
                continuation.resume(false)
            }
        }.start()

    }

}