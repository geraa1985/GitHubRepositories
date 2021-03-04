package com.geraa1985.githubrepositories.frameworks.cicerone

import com.geraa1985.githubrepositories.adapters.INavigation
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class NavigationImpl @Inject constructor(private val router: Router) : INavigation {

    override fun goBack() {
        router.exit()
    }

    override fun setRootScreenToReposList() {
        router.newRootScreen(FragmentScreen.reposScreen())
    }

    override fun goToUserScreen(login: String) {
        router.navigateTo(FragmentScreen.userScreen(login))
    }
}