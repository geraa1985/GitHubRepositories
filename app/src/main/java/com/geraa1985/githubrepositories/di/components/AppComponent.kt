package com.geraa1985.githubrepositories.di.components

import android.app.Application
import com.geraa1985.githubrepositories.adapters.main_activity.MainPresenter
import com.geraa1985.githubrepositories.adapters.repos_fragment.ReposPresenter
import com.geraa1985.githubrepositories.adapters.user_fragment.UserPresenter
import com.geraa1985.githubrepositories.di.modules.NavigationModule
import com.geraa1985.githubrepositories.di.modules.NetworkModule
import com.geraa1985.githubrepositories.di.modules.RepositoryModule
import com.geraa1985.githubrepositories.di.modules.UseCaseModule
import com.geraa1985.githubrepositories.frameworks.ui.activities.MainActivity
import com.geraa1985.githubrepositories.frameworks.ui.fragments.ReposFragment
import com.geraa1985.githubrepositories.frameworks.ui.fragments.UserFragment
import com.geraa1985.githubrepositories.frameworks.ui.rvadapters.ReposRVAdapter
import com.geraa1985.githubrepositories.usecases.Interactor
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NavigationModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(reposFragment: ReposFragment)
    fun inject(userFragment: UserFragment)
    fun inject(interactor: Interactor)
    fun inject(reposRVAdapter: ReposRVAdapter)
    fun inject(mainPresenter: MainPresenter)
    fun inject(reposPresenter: ReposPresenter)
    fun inject(userPresenter: UserPresenter)

}