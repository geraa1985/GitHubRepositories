package com.geraa1985.githubrepositories.frameworks.glide

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.geraa1985.githubrepositories.adapters.IImgLoader

class GlideImgLoader: IImgLoader<ImageView, RequestOptions> {

    override fun loadInto(url: String, container: ImageView, options: RequestOptions?) {
        options?.let{
            Glide
                .with(container.context)
                .load(url)
                .apply(options)
                .into(container)
        } ?: run {
            Glide
                .with(container.context)
                .load(url)
                .into(container)
        }
    }

}