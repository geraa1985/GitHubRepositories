package com.geraa1985.githubrepositories.adapters.main_activity

import com.geraa1985.githubrepositories.adapters.INavigation
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter: MvpPresenter<IMainView>() {

    @Inject
    lateinit var navigation: INavigation

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        navigation.setRootScreenToReposList()
    }

    fun backPressed() {
        navigation.goBack()
    }
}