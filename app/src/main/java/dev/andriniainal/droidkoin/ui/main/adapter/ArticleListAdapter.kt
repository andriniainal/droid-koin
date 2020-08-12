package dev.andriniainal.droidkoin.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dev.andriniainal.droidkoin.databinding.ItemArticleBinding
import dev.andriniainal.droidkoin.model.Article
import dev.andriniainal.droidkoin.ui.main.viewholder.ArticleViewHolder

class ArticleListAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<Article, ArticleViewHolder>(DIFF_CALLBACK) {

    private val mArticleList: MutableList<Article> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        createArticleViewHolder(parent)

    override fun getItemCount() =
        mArticleList.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) =
        holder.bind(
            mArticleList[position],
            onItemClickListener
        )

    private fun createArticleViewHolder(parent: ViewGroup) =
        ArticleViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    fun setArticles(articleList: List<Article>) {
        clearAllArticles()
        mArticleList.addAll(articleList)
        notifyDataSetChanged()
    }

    fun clearAllArticles() {
        mArticleList.clear()
    }

    interface OnItemClickListener {
        fun onItemClicked(article: Article, imageView: ImageView)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem

        }
    }
}