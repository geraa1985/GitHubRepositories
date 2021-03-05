package com.geraa1985.githubrepositories.frameworks.cicerone

import androidx.fragment.app.Fragment
import com.geraa1985.githubrepositories.frameworks.ui.fragments.ReposFragment
import com.geraa1985.githubrepositories.frameworks.ui.fragments.UserFragment
import moxy.MvpAppCompatFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class FragmentScreen(private val fragment: MvpAppCompatFragment): SupportAppScreen() {

    override fun getFragment(): Fragment {
        return fragment
    }

    companion object{
        fun reposScreen() = FragmentScreen(ReposFragment())
        fun userScreen(login: String) = FragmentScreen(UserFragment.newInstance(login))
    }

}