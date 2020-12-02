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

class MainViewModel(private val client: RetrofitClient = RetrofitClient()) : ViewModel() {
    private var _status = MutableLiveData<NetworkState>()
    val status: LiveData<NetworkState>
        get() = _status

    fun getPosts(): LiveData<List<Post>> {
       _status.value = NetworkState.LOADING
        val data = MutableLiveData<List<Post>>()

        viewModelScope.launch {
            try {
                val result = client.apiService.getPosts()
                if (result.isSuccessful) {
                    data.value = result.body()
                    _status.value = NetworkState.SUCCESS
                } else {
                    data.value = null
                    _status.value = NetworkState.error(result.message())
                }
            } catch (error: IOException) {
                _status.value = NetworkState.error(error.message)
            }
        }
        return data
    }
}