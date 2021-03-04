package com.geraa1985.githubrepositories.adapters.user_fragment

import com.geraa1985.githubrepositories.adapters.IInteractor
import com.geraa1985.githubrepositories.adapters.INavigation
import kotlinx.coroutines.*
import moxy.MvpPresenter
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UserPresenter: MvpPresenter<IUserView>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + Job()

    @Inject
    lateinit var navigation: INavigation

    @Inject
    lateinit var interactor: IInteractor

    fun setUser(login: String) {
        launch {
            try {
                val user = interactor.getUser(login)
                user?.let {
                    withContext(Dispatchers.Main) {
                        viewState.apply {
                            showLogin(user.login)
                            showAvatar(user.avatarUrl)
                            showName(user.name)
                            showCompany(user.company)
                            showBlog(user.site)
                            showLocation(user.location)
                            showEmail(user.email)
                            showBio(user.bio)
                            showTwitter(user.twitterUsername)
                            showFollow(user.followers.toString(), user.following.toString())
                        }
                    }
                }
            } catch (e: Throwable) {
                e.message?.let {
                    withContext(Dispatchers.Main) { viewState.showError(it) }
                }
            }

        }
    }

    fun backPressed(): Boolean {
        navigation.goBack()
        return true
    }
}