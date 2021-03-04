package com.geraa1985.githubrepositories.adapters.repos_fragment

import com.geraa1985.githubrepositories.adapters.IInteractor
import com.geraa1985.githubrepositories.adapters.INavigation
import kotlinx.coroutines.*
import moxy.MvpPresenter
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ReposPresenter : MvpPresenter<IReposView>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + Job()

    @Inject
    lateinit var navigation: INavigation

    @Inject
    lateinit var interactor: IInteractor

    private var currentPage = 1
    private var totalPages = 1
    private lateinit var currentQuery: String

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRvRepos()
    }

    fun searchRepos(query: String?) {
        viewState.showProgress()
        currentPage = 1
        totalPages = 1
        if (query != null && query.isNotEmpty()) {
            launch {
                try {
                    val repos = interactor.getRepos(query, currentPage)
                    if (repos.isNullOrEmpty()) {
                        withContext(Dispatchers.Main) {
                            viewState.hideProgress()
                            viewState.noSuchRepos(query)
                        }
                    } else {
                        currentQuery = query
                        totalPages = interactor.getTotalPages()
                        withContext(Dispatchers.Main) {
                            viewState.hideProgress()
                            viewState.updateRepos(repos)
                        }
                    }
                } catch (e: Throwable) {
                    e.message?.let {
                        withContext(Dispatchers.Main) {
                            viewState.hideProgress()
                            viewState.showError(it)
                        }
                    }
                }
            }
        }
    }

    private var isLoadingPage = false

    fun isLoading() = isLoadingPage

    fun loadPage() {
        isLoadingPage = true
        launch {
            if (currentPage < totalPages) {
                try {
                    val newPage = currentPage++
                    val newRepos = interactor.getNewPage(currentQuery, newPage)
                    newRepos?.let {
                        withContext(Dispatchers.Main) {
                            viewState.updateRepos(newRepos)
                        }
                    }
                } catch (e: Throwable) {
                    e.message?.let {
                        withContext(Dispatchers.Main) { viewState.showError(it) }
                    }
                }
            }
            isLoadingPage = false
        }
    }

    fun itemClicked(login: String) {
        navigation.goToUserScreen(login)
    }


    fun backPressed(): Boolean {
        navigation.goBack()
        return true
    }

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }
}