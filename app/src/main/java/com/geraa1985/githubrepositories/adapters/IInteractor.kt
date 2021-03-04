package com.geraa1985.githubrepositories.adapters

import com.geraa1985.githubrepositories.entities.GitHubRepo
import com.geraa1985.githubrepositories.entities.GitHubUser

interface IInteractor {
    suspend fun getRepos(repo: String, page: Int): List<GitHubRepo>?
    suspend fun getUser(login: String): GitHubUser?
    fun getTotalPages(): Int
}