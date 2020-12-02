package com.suadahaji.postsapplication.utils

enum class Status {
    LOADING,
    SUCCESS,
    FAILED
}

data class NetworkState constructor(val status: Status, val msg: String? = null) {
    companion object {
        val SUCCESS = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.LOADING)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }
}