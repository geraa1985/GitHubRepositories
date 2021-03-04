package com.geraa1985.githubrepositories.frameworks.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.request.RequestOptions
import com.geraa1985.githubrepositories.adapters.IImgLoader
import com.geraa1985.githubrepositories.adapters.user_fragment.IUserView
import com.geraa1985.githubrepositories.adapters.user_fragment.UserPresenter
import com.geraa1985.githubrepositories.app.MyApp
import com.geraa1985.githubrepositories.databinding.FragmentUserBinding
import com.geraa1985.githubrepositories.frameworks.cicerone.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UserFragment : MvpAppCompatFragment(), IUserView, BackButtonListener {

    companion object {
        private const val USER_KEY = "login"
        fun newInstance(login: String) = UserFragment().apply {
            arguments = Bundle().apply {
                putString(USER_KEY, login)
            }
        }
    }

    private lateinit var binding: FragmentUserBinding

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter().apply { MyApp.instance.appComponent.inject(this) }
    }

    @Inject
    lateinit var imgLoader: IImgLoader<ImageView, RequestOptions>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApp.instance.appComponent.inject(this)
    }

    override fun onStart() {
        super.onStart()

        arguments?.getString(USER_KEY)?.let {
            presenter.setUser(it)
        }
    }

    override fun showLogin(login: String?) {
        if (login != null && login.isNotEmpty()) {
            binding.toolbar.title = login
        }
    }

    override fun showAvatar(avatarUrl: String?) {
        if (avatarUrl != null && avatarUrl.isNotEmpty()) {
            imgLoader.loadInto(avatarUrl, binding.userAvatar, null)
        }
    }

    override fun showName(name: String?) {
        if (name != null && name.isNotEmpty()) {
            binding.userName.text = name
        } else { binding.userName.visibility = View.GONE }
    }

    override fun showCompany(company: String?) {
        if (company != null && company.isNotEmpty()) {
            binding.userCompany.text = company
        } else { binding.userCompany.visibility = View.GONE }
    }

    override fun showBlog(blog: String?) {
        if (blog != null && blog.isNotEmpty()) {
            binding.userBlog.text = blog
        } else { binding.userBlog.visibility = View.GONE }
    }

    override fun showLocation(location: String?) {
        if (location != null && location.isNotEmpty()) {
            binding.userLocation.text = location
        } else { binding.userLocation.visibility = View.GONE }
    }

    override fun showEmail(email: String?) {
        if (email != null && email.isNotEmpty()) {
            binding.userEmail.text = email
        } else { binding.userEmail.visibility = View.GONE }
    }

    override fun showBio(bio: String?) {
        if (bio != null && bio.isNotEmpty()) {
            binding.userBio.text = bio
        } else { binding.userBio.visibility = View.GONE }
    }

    @SuppressLint("SetTextI18n")
    override fun showTwitter(twitter: String?) {
        if (twitter != null && twitter.isNotEmpty()) {
            binding.userCompany.text = "https://twitter.com/${twitter}"
        } else { binding.userTwitter.visibility = View.GONE }
    }

    @SuppressLint("SetTextI18n")
    override fun showFollow(followers: String, following: String) {
        binding.userFollow.text = "followers/following: $followers / $following"
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }
}