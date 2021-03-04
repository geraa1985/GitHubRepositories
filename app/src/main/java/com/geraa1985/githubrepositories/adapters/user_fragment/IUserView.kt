package com.geraa1985.githubrepositories.adapters.user_fragment

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface IUserView: MvpView {
    fun showLogin(login: String?)
    fun showAvatar(avatarUrl: String?)
    fun showName(name: String?)
    fun showCompany(company: String?)
    fun showBlog(blog: String?)
    fun showLocation(location: String?)
    fun showEmail(email: String?)
    fun showBio(bio: String?)
    fun showTwitter(twitter: String?)
    fun showFollow(followers: String, following: String)
    @OneExecution
    fun showError(message: String)
}