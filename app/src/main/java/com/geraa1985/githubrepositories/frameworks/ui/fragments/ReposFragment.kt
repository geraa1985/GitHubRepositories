package com.geraa1985.githubrepositories.frameworks.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geraa1985.githubrepositories.R
import com.geraa1985.githubrepositories.adapters.repos_fragment.IReposView
import com.geraa1985.githubrepositories.adapters.repos_fragment.ReposPresenter
import com.geraa1985.githubrepositories.app.MyApp
import com.geraa1985.githubrepositories.databinding.FragmentReposBinding
import com.geraa1985.githubrepositories.entities.GitHubRepo
import com.geraa1985.githubrepositories.frameworks.cicerone.BackButtonListener
import com.geraa1985.githubrepositories.frameworks.ui.rvadapters.ReposDiffUtilCallback
import com.geraa1985.githubrepositories.frameworks.ui.rvadapters.ReposRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ReposFragment : MvpAppCompatFragment(), IReposView, BackButtonListener {

    private lateinit var binding: FragmentReposBinding

    private val presenter: ReposPresenter by moxyPresenter {
        ReposPresenter().apply { MyApp.instance.appComponent.inject(this) }
    }

    private var adapter: ReposRVAdapter? = null
    private lateinit var searchItem: MenuItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val mainToolbar = binding.reposToolbar
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(mainToolbar)
        setHasOptionsMenu(true)

        binding.rvRepos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = (recyclerView.layoutManager as LinearLayoutManager).childCount
                val totalItemCount = (recyclerView.layoutManager as LinearLayoutManager).itemCount
                val firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                presenter.loadPage(visibleItemCount,totalItemCount,firstVisibleItem)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_search_menu, menu)
        searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Enter repo"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.searchRepos(newText)
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun initRvRepos() {
        binding.rvRepos.layoutManager = LinearLayoutManager(requireContext())
        adapter = ReposRVAdapter().apply { MyApp.instance.appComponent.inject(this) }
        binding.rvRepos.adapter = adapter
        adapter?.setOnItemClickListener(object : ReposRVAdapter.OnItemClickListener {
            override fun onClick(login: String?) {
                login?.let {
                    presenter.itemClicked(login)
                }
            }
        })
    }

    override fun updateRepos(repos: List<GitHubRepo>) {
        adapter?.let {
            if (it.getData().isNullOrEmpty()) {
                it.setData(repos)
            } else {
                val oldList = it.getData()
                val newList = it.getData().apply { addAll(repos) }

                val reposDiffUtilCallback = ReposDiffUtilCallback(oldList, newList)
                val reposDiffResult = DiffUtil.calculateDiff(reposDiffUtilCallback)
                reposDiffResult.dispatchUpdatesTo(it)
            }
        }
    }

    override fun showError(message: String) =
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()


    override fun noSuchRepos(repo: String) {
            val message = "No such repos: $repo"
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    override fun onStop() {
        searchItem.collapseActionView()
        super.onStop()
    }

}