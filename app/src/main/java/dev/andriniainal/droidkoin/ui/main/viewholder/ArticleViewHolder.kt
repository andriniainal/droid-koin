package dev.andriniainal.droidkoin.ui.main.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.andriniainal.droidkoin.databinding.ItemArticleBinding
import dev.andriniainal.droidkoin.model.Article
import dev.andriniainal.droidkoin.ui.main.adapter.ArticleListAdapter

class ArticleViewHolder(private val binding: ItemArticleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        article: Article,
        onItemClickListener: ArticleListAdapter.OnItemClickListener? = null
    ) {
        binding.sourceTextView.text = article.source?.name
        binding.titleTextView.text = article.title
        binding.descriptionTextView.text = article.description
        Glide.with(binding.imageView.context)
            .load(article.urlToImage)
            .into(binding.imageView)

        onItemClickListener?.let { listener ->
            binding.root.setOnClickListener {
                listener.onItemClicked(article, binding.imageView)
            }
        }
    }
}