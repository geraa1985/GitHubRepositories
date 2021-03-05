package com.geraa1985.githubrepositories.frameworks.ui.activities

import android.os.Bundle
import com.geraa1985.githubrepositories.adapters.main_activity.IMainView
import com.geraa1985.githubrepositories.adapters.main_activity.MainPresenter
import com.geraa1985.githubrepositories.app.MyApp
import com.geraa1985.githubrepositories.databinding.ActivityMainBinding
import com.geraa1985.githubrepositories.frameworks.cicerone.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), IMainView {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val presenter: MainPresenter by moxyPresenter {
        MainPresenter().apply { MyApp.instance.appComponent.inject(this) }
    }

    private val navigator by lazy {
        SupportAppNavigator(this, supportFragmentManager, binding.fragmentsHost.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MyApp.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backPressed()
    }
}