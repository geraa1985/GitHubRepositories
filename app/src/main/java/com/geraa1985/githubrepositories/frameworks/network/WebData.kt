package com.geraa1985.githubrepositories.frameworks.network

import com.geraa1985.githubrepositories.adapters.repository.IWeb
import com.geraa1985.githubrepositories.entities.GitHubUser
import com.geraa1985.githubrepositories.entities.SearchResult
import javax.inject.Inject

class WebData @Inject constructor(private val gitHubApi: IGitHubData): IWeb {

    override suspend fun getRepos(repo: String, perPage: Int, page: Int): SearchResult? =
        gitHubApi.searchRepos(repo, perPage, page)

    override suspend fun getUser(login: String): GitHubUser? =
        gitHubApi.getUser(login)

}