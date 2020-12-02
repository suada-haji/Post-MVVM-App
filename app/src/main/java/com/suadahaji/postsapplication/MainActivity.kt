package com.suadahaji.postsapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.suadahaji.postsapplication.ui.PostListAdapter
import com.suadahaji.postsapplication.ui.PostListViewModel
import com.suadahaji.postsapplication.utils.NetworkState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_layout.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: PostListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(PostListViewModel::class.java)
        viewModel.getPosts()
        viewModel.posts.observe(this, { posts ->
            postRecycler.addItemDecoration(
                DividerItemDecoration(postRecycler.context, LinearLayoutManager.VERTICAL)
            )
            postRecycler.adapter = PostListAdapter(posts)
        })

        viewModel.status.observe(this, { status ->
            when(status) {
                NetworkState.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    errorLayout.visibility = View.GONE
                }
                NetworkState.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    errorLayout.visibility = View.GONE
                }
                NetworkState.error(status.msg) -> {
                    progressBar.visibility = View.GONE
                    errorLayout.visibility = View.VISIBLE
                    errorLayout.errorText.text = getString(R.string.no_internet)
                }
            }
        })

        errorLayout.retryBtn.setOnClickListener { viewModel.getPosts() }
    }
}