package com.suadahaji.postsapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.suadahaji.postsapplication.ui.PostListAdapter
import com.suadahaji.postsapplication.ui.PostListViewModel
import kotlinx.android.synthetic.main.activity_main.*

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
    }
}