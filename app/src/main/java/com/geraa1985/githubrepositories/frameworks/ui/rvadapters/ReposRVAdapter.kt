package com.geraa1985.githubrepositories.frameworks.ui.rvadapters

import android.annotation.SuppressLint
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.geraa1985.githubrepositories.R
import com.geraa1985.githubrepositories.adapters.IImgLoader
import com.geraa1985.githubrepositories.databinding.RvItemRepoBinding
import com.geraa1985.githubrepositories.entities.GitHubRepo
import javax.inject.Inject

class ReposRVAdapter :
    RecyclerView.Adapter<ReposRVAdapter.RecyclerItemViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    private var data: List<GitHubRepo>? = null

    @Inject
    lateinit var imgLoader: IImgLoader<ImageView, RequestOptions>

    fun setData(data: List<GitHubRepo>) {
        this.data = data
        notifyDataSetChanged()
    }

    private lateinit var binding: RvItemRepoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        binding = RvItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        data?.get(position)?.let { holder.bind(it) }
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.rv_repos_anims)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    inner class RecyclerItemViewHolder(
        private val binding: RvItemRepoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(data: GitHubRepo) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                val content = SpannableString(data.name)
                content.setSpan(UnderlineSpan(), 0, content.length, 0)
                binding.repoName.text = content
                binding.repoDescription.text = data.description
                binding.repoLang.text = "Lang: ${data.language}"
                binding.repoUpdate.text = "Upd: ${data.updatedAt?.substring(0, 10)}"
                binding.repoStars.text = "Stars: ${data.stars.toString()}"
                data.owner?.avatarUrl?.let {
                    imgLoader.loadInto(
                        it, binding.ownerAvatar,
                        RequestOptions().circleCrop())
                }

                itemView.setOnClickListener {
                    onItemClickListener?.onClick(
                        data.owner?.login
                    )
                }
            }
        }

    }

    interface OnItemClickListener {
        fun onClick(
            login: String?
        )
    }

    internal fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
}
