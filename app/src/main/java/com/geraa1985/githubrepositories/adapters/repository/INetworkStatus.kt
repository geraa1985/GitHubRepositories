package com.geraa1985.githubrepositories.adapters.repository

interface INetworkStatus {
    suspend fun isConnected(): Boolean
}