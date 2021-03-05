package com.geraa1985.githubrepositories.adapters

interface INavigation {
    fun goBack()
    fun setRootScreenToReposList()
    fun goToUserScreen(login: String)
}