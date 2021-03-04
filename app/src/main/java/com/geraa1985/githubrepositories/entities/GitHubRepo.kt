package com.geraa1985.githubrepositories.entities

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitHubRepo(
    @Expose @SerializedName("name") val name: String?,
    @Expose @SerializedName("description") val description: String?,
    @Expose @SerializedName("language") val language: String?,
    @Expose @SerializedName("updated_at") val updatedAt: String?,
    @Expose @SerializedName("owner") val owner: RepoOwner?,
    @Expose @SerializedName("stargazers_count") val stars: Int?
): Parcelable