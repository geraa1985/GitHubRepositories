package com.geraa1985.githubrepositories.adapters.repos_fragment

import com.geraa1985.githubrepositories.entities.GitHubRepo
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface IReposView: MvpView {
    fun initRvRepos()
    fun updateRepos(repos: List<GitHubRepo>)
    fun showProgress()
    fun hideProgress()
    @OneExecution
    fun showError(message: String)
    @OneExecution
    fun noSuchRepos(repo: String)
}