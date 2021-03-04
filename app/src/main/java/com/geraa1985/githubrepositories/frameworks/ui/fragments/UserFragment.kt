package com.geraa1985.githubrepositories.frameworks.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geraa1985.githubrepositories.R
import moxy.MvpAppCompatFragment

class UserFragment : MvpAppCompatFragment() {

    companion object{
        private const val USER_KEY = "login"
        fun newInstance(login: String) = UserFragment().apply {
            arguments = Bundle().apply {
                putString(USER_KEY, login)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }


}