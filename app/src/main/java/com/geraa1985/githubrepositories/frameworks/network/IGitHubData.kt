package com.geraa1985.githubrepositories.frameworks.network

import com.geraa1985.githubrepositories.entities.GitHubUser
import com.geraa1985.githubrepositories.entities.SearchResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IGitHubData {

    @GET("search/repositories")
    suspend fun searchRepos(@Query("q") repo: String): SearchResult?

    @GET("users/{login}")
    suspend fun getUser(@Path("login") login: String): GitHubUser?

}