package com.suadahaji.postsapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suadahaji.postsapplication.model.Post
import com.suadahaji.postsapplication.network.RetrofitClient
import com.suadahaji.postsapplication.utils.NetworkState
import kotlinx.coroutines.launch
import java.io.IOException

class PostListViewModel(private val client: RetrofitClient = RetrofitClient()) : ViewModel() {
    private var _status = MutableLiveData<NetworkState>()
    val status: LiveData<NetworkState>
        get() = _status

    private var _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts

    fun getPosts() {
       _status.value = NetworkState.LOADING

        viewModelScope.launch {
            try {
                val result = client.apiService.getPosts()
                if (result.isSuccessful) {
                    _status.value = NetworkState.SUCCESS
                    _posts.value = result.body()
                } else {
                    _status.value = NetworkState.error(result.message())
                    _posts.value = null
                }
            } catch (error: IOException) {
                _status.value = NetworkState.error(error.message)
            }
        }
    }
}