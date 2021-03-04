package com.geraa1985.githubrepositories.di.modules

import com.geraa1985.githubrepositories.adapters.repository.INetworkStatus
import com.geraa1985.githubrepositories.adapters.repository.IWeb
import com.geraa1985.githubrepositories.adapters.repository.Repository
import com.geraa1985.githubrepositories.usecases.IRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun repo(
        web: IWeb,
        networkStatus: INetworkStatus
    ): IRepository = Repository(web, networkStatus)
}