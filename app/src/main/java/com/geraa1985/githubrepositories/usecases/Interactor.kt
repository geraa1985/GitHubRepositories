package com.geraa1985.githubrepositories.usecases

import com.geraa1985.githubrepositories.adapters.IInteractor
import com.geraa1985.githubrepositories.app.MyApp
import com.geraa1985.githubrepositories.entities.GitHubRepo
import com.geraa1985.githubrepositories.entities.GitHubUser
import javax.inject.Inject

class Interactor: IInteractor {

    @Inject
    lateinit var repository: IRepository

    init {
        MyApp.instance.appComponent.inject(this)
    }

    override suspend fun getRepos(repo: String, page: Int): List<GitHubRepo>? = repository.getRepos(repo, page)
    override suspend fun getUser(login: String): GitHubUser? = repository.getUser(login)
    override fun getTotalPages(): Int = repository.getTotalPages()

}