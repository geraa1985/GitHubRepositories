package com.geraa1985.githubrepositories.adapters.repository

import com.geraa1985.githubrepositories.entities.GitHubUser
import com.geraa1985.githubrepositories.entities.SearchResult


interface IWeb {
    suspend fun getRepos(repo: String): SearchResult?
    suspend fun getUser(login: String): GitHubUser?
}