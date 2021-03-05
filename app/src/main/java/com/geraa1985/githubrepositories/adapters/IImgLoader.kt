package com.geraa1985.githubrepositories.adapters

interface IImgLoader<T, O> {
    fun loadInto(url: String, container: T, options: O?)
}