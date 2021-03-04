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

    override suspend fun getRepos(repo: String): List<GitHubRepo>? = repository.getRepos(repo)
    override suspend fun getUser(login: String): GitHubUser? = repository.getUser(login)

}