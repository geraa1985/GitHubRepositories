package com.geraa1985.githubrepositories.entities

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepoOwner(
    @Expose @SerializedName("avatar_url") val avatarUrl: String?,
    @Expose @SerializedName("login") val login: String?
): Parcelable