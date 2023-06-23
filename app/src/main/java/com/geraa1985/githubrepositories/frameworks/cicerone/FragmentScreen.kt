package com.geraa1985.githubrepositories.frameworks.cicerone

import androidx.fragment.app.Fragment
import com.geraa1985.githubrepositories.frameworks.ui.fragments.ReposFragment
import com.geraa1985.githubrepositories.frameworks.ui.fragments.UserFragment
import moxy.MvpAppCompatFragment
import com.github.terrakok.cicerone.Screen

class FragmentScreen(private val fragment: MvpAppCompatFragment): Screen {

    companion object{
        fun reposScreen() = FragmentScreen(ReposFragment())
        fun userScreen(login: String) = FragmentScreen(UserFragment.newInstance(login))
    }

}