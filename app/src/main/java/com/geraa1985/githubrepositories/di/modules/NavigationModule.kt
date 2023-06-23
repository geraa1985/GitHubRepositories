package com.geraa1985.githubrepositories.di.modules

import com.geraa1985.githubrepositories.adapters.INavigation
import com.geraa1985.githubrepositories.frameworks.cicerone.NavigationImpl
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {

    @Singleton
    @Provides
    fun cicerone(): Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun navigatorHolder(cicerone: Cicerone<Router>) = cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun router(cicerone: Cicerone<Router>) = cicerone.router

    @Singleton
    @Provides
    fun navigation(router: Router): INavigation = NavigationImpl(router)

}