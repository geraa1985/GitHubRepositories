package com.geraa1985.githubrepositories.adapters.repos_fragment

import com.geraa1985.githubrepositories.entities.GitHubRepo
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface IReposView: MvpView {
    fun initRvRepos()
    fun updateRepos(repos: List<GitHubRepo>)
    fun showError(message: String)
    fun noSuchRepos(repo: String)
    fun showProgress()
    fun hideProgress()
}