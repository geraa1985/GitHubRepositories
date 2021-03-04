package com.geraa1985.githubrepositories.adapters.repos_fragment

import com.geraa1985.githubrepositories.adapters.INavigation
import com.geraa1985.githubrepositories.usecases.Interactor
import kotlinx.coroutines.*
import moxy.MvpPresenter
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ReposPresenter(private val interactor: Interactor = Interactor()) :
    MvpPresenter<IReposView>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + Job()

    @Inject
    lateinit var navigation: INavigation

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRvRepos()
    }

    fun searchRepos(query: String?) {
        if (query != null && query.isNotEmpty()) {
            launch {
                try {
                    val repos = interactor.getData(query)
                    if (repos.isNullOrEmpty()) {
                        withContext(Dispatchers.Main) { viewState.noSuchRepos(query) }
                    } else {
                        withContext(Dispatchers.Main) { viewState.updateRepos(repos) }
                    }
                } catch (e: Throwable) {
                    e.message?.let {
                        withContext(Dispatchers.Main) { viewState.showError(it) }
                    }
                }

            }
        }
    }

    fun itemClicked(login: String) {
        navigation.goToUserScreen(login)
    }


    fun backPressed(): Boolean {
        navigation.goBack()
        return true
    }
}