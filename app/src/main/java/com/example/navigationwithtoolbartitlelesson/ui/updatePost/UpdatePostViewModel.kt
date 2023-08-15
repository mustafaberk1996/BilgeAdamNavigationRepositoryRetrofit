package com.example.navigationwithtoolbartitlelesson.ui.updatePost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationwithtoolbartitlelesson.data.api.model.Post
import com.example.navigationwithtoolbartitlelesson.data.respository.PostRepositoryImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdatePostViewModel:ViewModel(){


    private val postRepository = PostRepositoryImpl()

    private val _post:MutableStateFlow<Post?> = MutableStateFlow(null)
    val post:StateFlow<Post?> = _post

    private val _postUpdate:MutableSharedFlow<Boolean> = MutableSharedFlow()
    val postUpdate:SharedFlow<Boolean> = _postUpdate




    fun update(body: String, title: String) {
        viewModelScope.launch {
            if (_post.value != null){
                val updatedPost = _post.value!!.copy(body = body, title = title)
                _postUpdate.emit(postRepository.update(updatedPost))
            }
        }
    }

    fun getPost(postId: Int) {
        viewModelScope.launch {
            val post = postRepository.getPostById(postId)
            _post.emit(post)
        }
    }
}