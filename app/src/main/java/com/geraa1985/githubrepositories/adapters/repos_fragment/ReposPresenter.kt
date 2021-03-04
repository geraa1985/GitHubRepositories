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
    private val totalPages: Int by lazy { interactor.getTotalPages() }
    private lateinit var currentQuery: String

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRvRepos()
    }

    fun searchRepos(query: String?) {
        if (query != null && query.isNotEmpty()) {
            launch {
                try {
                    val repos = interactor.getRepos(query, currentPage)
                    if (repos.isNullOrEmpty()) {
                        withContext(Dispatchers.Main) { viewState.noSuchRepos(query) }
                    } else {
                        currentQuery = query
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

    fun loadPage(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int) {
        if ((visibleItemCount + firstVisibleItem + 3) > totalItemCount && currentPage < totalPages) {
            launch {
                val newRepos = interactor.getRepos(currentQuery, ++currentPage)
                if (!newRepos.isNullOrEmpty()) {
                    viewState.updateRepos(newRepos)
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