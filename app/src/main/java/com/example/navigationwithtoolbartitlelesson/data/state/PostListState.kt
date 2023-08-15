package com.example.navigationwithtoolbartitlelesson.data.state

import com.example.navigationwithtoolbartitlelesson.data.api.model.Post

sealed class PostListState {
    object Idle : PostListState()
    object Loading : PostListState()
    class Result(val posts: List<Post>) : PostListState()
    class Error(val throwable: Throwable) : PostListState()
}