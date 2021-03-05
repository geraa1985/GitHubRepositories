package com.geraa1985.githubrepositories.di.modules

import com.geraa1985.githubrepositories.adapters.IInteractor
import com.geraa1985.githubrepositories.usecases.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Singleton
    @Provides
    fun getInteractor(): IInteractor = Interactor()
}