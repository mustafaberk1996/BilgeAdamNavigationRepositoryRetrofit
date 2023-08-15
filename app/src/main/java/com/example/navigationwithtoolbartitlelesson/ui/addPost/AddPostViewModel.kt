package com.example.navigationwithtoolbartitlelesson.ui.addPost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationwithtoolbartitlelesson.data.api.RetrofitHelper
import com.example.navigationwithtoolbartitlelesson.data.api.model.Post
import com.example.navigationwithtoolbartitlelesson.data.api.service.PostService
import com.example.navigationwithtoolbartitlelesson.data.state.CreatePostState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddPostViewModel:ViewModel() {

    private val _createPostState:MutableStateFlow<CreatePostState> = MutableStateFlow(CreatePostState.Idle)
    val createPostState:StateFlow<CreatePostState> = _createPostState

    fun createPost(title: String, body: String) {
        viewModelScope.launch {
            runCatching {
                //TODO retrofite/service ulas, createpost functionu cagir icerisine post objesini ver
                _createPostState.value = CreatePostState.Loading
                val service = RetrofitHelper.retrofit().create(PostService::class.java)
                val post = Post(body = body, title = title, userId = 1)
                val createdPost = service.create(post)
                _createPostState.value = CreatePostState.Success
                println(createdPost)
            }.onFailure {
                //TODO hatayi ele al
                _createPostState.value = CreatePostState.Error(it)
            }
        }

    }



}