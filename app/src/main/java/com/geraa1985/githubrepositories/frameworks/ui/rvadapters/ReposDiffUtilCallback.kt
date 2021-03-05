package com.geraa1985.githubrepositories.frameworks.ui.rvadapters

import androidx.recyclerview.widget.DiffUtil
import com.geraa1985.githubrepositories.entities.GitHubRepo

class ReposDiffUtilCallback(
    private val oldList: List<GitHubRepo>,
    private val newList: List<GitHubRepo>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldRepo = oldList[oldItemPosition]
        val newRepo = newList[newItemPosition]
        return oldRepo.language.equals(newRepo.language) && oldRepo.updatedAt.equals(newRepo.updatedAt) && oldRepo.description.equals(newRepo.description)
    }
}