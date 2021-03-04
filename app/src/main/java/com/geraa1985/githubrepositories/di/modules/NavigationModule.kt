package com.geraa1985.githubrepositories.di.modules

import com.geraa1985.githubrepositories.adapters.INavigation
import com.geraa1985.githubrepositories.frameworks.cicerone.NavigationImpl
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class NavigationModule {

    @Singleton
    @Provides
    fun cicerone(): Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun navigatorHolder(cicerone: Cicerone<Router>) = cicerone.navigatorHolder

    @Singleton
    @Provides
    fun router(cicerone: Cicerone<Router>) = cicerone.router

    @Singleton
    @Provides
    fun navigation(router: Router): INavigation = NavigationImpl(router)

}