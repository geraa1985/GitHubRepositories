package com.geraa1985.githubrepositories.entities

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitHubUser(
    @Expose @SerializedName("avatar_url") val avatarUrl: String?,
    @Expose @SerializedName("login") val login: String?,
    @Expose @SerializedName("name") val name: String?,
    @Expose @SerializedName("company") val company: String?,
    @Expose @SerializedName("blog") val site: String?,
    @Expose @SerializedName("location") val location: String?,
    @Expose @SerializedName("email") val email: String?,
    @Expose @SerializedName("bio") val bio: String?,
    @Expose @SerializedName("twitter_username") val twitterUsername: String?,
    @Expose @SerializedName("followers") val followers: Int,
    @Expose @SerializedName("following") val following: Int
): Parcelable