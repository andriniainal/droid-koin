package dev.andriniainal.droidkoin.ui.main

import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.andriniainal.droidkoin.R
import dev.andriniainal.droidkoin.databinding.ActivityMainBinding
import dev.andriniainal.droidkoin.model.Article
import dev.andriniainal.droidkoin.ui.base.BaseActivity
import dev.andriniainal.droidkoin.ui.main.adapter.ArticleListAdapter
import dev.andriniainal.droidkoin.utils.State
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(),
    ArticleListAdapter.OnItemClickListener {

    private lateinit var mAdapter: ArticleListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)  // Set AppTheme before setting content view.

        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        mAdapter = ArticleListAdapter(this)

        // Initialize RecyclerView
        mViewBinding.articleRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = mAdapter
        }

        initArticles()
    }

    private fun initArticles() {
        mViewModel.articlesLiveData.observe(this, Observer { state ->
            when (state) {
//                is State.Loading -> showLoading(true)
                is State.Success -> {
                    mAdapter.setArticles(state.data.articles)
//                    showLoading(false)
                }
                is State.Error -> {
//                    showToast(state.message)
//                    showLoading(false)
                }
            }
        })

        // If State isn't `Success` then reload articles.
        if (mViewModel.articlesLiveData.value !is State.Success) {
            getArticles()
        }
    }

    private fun getArticles() {
        mViewModel.getArticles()
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override val mViewModel: MainViewModel by viewModel()

    override fun onItemClicked(article: Article, imageView: ImageView) {
    }
}