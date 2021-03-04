package com.geraa1985.githubrepositories.adapters.repository

import com.geraa1985.githubrepositories.entities.GitHubRepo
import com.geraa1985.githubrepositories.entities.GitHubUser
import com.geraa1985.githubrepositories.usecases.IRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class Repository @Inject constructor(
    private val web: IWeb,
    private val networkStatus: INetworkStatus
) : IRepository, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + Job()

    private val perPage = 50
    private var totalPages = 0

    private val isConnected = async {
        networkStatus.isConnected()
    }

    override suspend fun getRepos(repo: String, page: Int): List<GitHubRepo>? =
        if (isConnected.await()) {
            web.getRepos(repo, perPage, page)?.totalCount?.let { totalPages = it.div(perPage) }
            web.getRepos(repo, perPage, page)?.reposList
        } else {
            null
        }

    override suspend fun getNewPage(repo: String, page: Int): List<GitHubRepo>? =
        if (isConnected.await()) {
            web.getRepos(repo, perPage, page)?.reposList
        } else {
            null
        }

    override suspend fun getUser(login: String): GitHubUser? =
        if (isConnected.await()) {
            web.getUser(login)
        } else {
            null
        }

    override fun getTotalPages(): Int = totalPages

}
