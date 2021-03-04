package com.geraa1985.githubrepositories.usecases

import com.geraa1985.githubrepositories.entities.GitHubRepo
import com.geraa1985.githubrepositories.entities.GitHubUser

interface IRepository {
    suspend fun getRepos(repo: String): List<GitHubRepo>?
    suspend fun getUser(login: String): GitHubUser?
}