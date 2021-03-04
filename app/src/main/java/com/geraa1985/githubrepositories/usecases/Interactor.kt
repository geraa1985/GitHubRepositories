package com.geraa1985.githubrepositories.usecases

import com.geraa1985.githubrepositories.app.MyApp
import com.geraa1985.githubrepositories.entities.GitHubRepo
import javax.inject.Inject

class Interactor {

    @Inject
    lateinit var repository: IRepository

    init {
        MyApp.instance.appComponent.inject(this)
    }

    suspend fun getData(repo: String): List<GitHubRepo>? = repository.getRepos(repo)
}