package com.geraa1985.githubrepositories.entities

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResult(
    @Expose @SerializedName("items") val reposList: List<GitHubRepo>?,
    @Expose @SerializedName("total_count") val totalCount: Int?
): Parcelable