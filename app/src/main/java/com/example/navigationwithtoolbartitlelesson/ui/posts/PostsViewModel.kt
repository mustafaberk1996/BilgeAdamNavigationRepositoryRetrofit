package com.example.navigationwithtoolbartitlelesson.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationwithtoolbartitlelesson.data.api.RetrofitHelper
import com.example.navigationwithtoolbartitlelesson.data.api.service.PostService
import com.example.navigationwithtoolbartitlelesson.data.respository.PostRepositoryImpl
import com.example.navigationwithtoolbartitlelesson.data.state.PostListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostsViewModel:ViewModel() {


    private val _postListState:MutableStateFlow<PostListState> = MutableStateFlow(PostListState.Idle)
    val postListState:StateFlow<PostListState> = _postListState

    private val postRepository = PostRepositoryImpl()

    fun getAllPosts(){
        viewModelScope.launch {
            kotlin.runCatching {
                _postListState.value = PostListState.Loading
                val posts = postRepository.getPosts()
                _postListState.value = PostListState.Result(posts)
            }.onFailure {
                _postListState.value = PostListState.Error(it)
            }
        }
    }
}