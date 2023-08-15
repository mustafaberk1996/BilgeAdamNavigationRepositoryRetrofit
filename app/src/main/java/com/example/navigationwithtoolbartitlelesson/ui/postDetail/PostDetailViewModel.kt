package com.example.navigationwithtoolbartitlelesson.ui.postDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationwithtoolbartitlelesson.data.api.RetrofitHelper
import com.example.navigationwithtoolbartitlelesson.data.api.service.PostService
import com.example.navigationwithtoolbartitlelesson.data.state.PostDeleteState
import com.example.navigationwithtoolbartitlelesson.data.state.PostDetailState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.create

class PostDetailViewModel:ViewModel() {

    private val _postDetailState:MutableStateFlow<PostDetailState> = MutableStateFlow(PostDetailState.Idle)
    val postDetailState:StateFlow<PostDetailState> = _postDetailState

    private val _postDeleteState:MutableSharedFlow<PostDeleteState> = MutableSharedFlow()
    val postDeleteState:SharedFlow<PostDeleteState> = _postDeleteState


    fun getPostById(id:Int){
        viewModelScope.launch {
            runCatching {
                _postDetailState.value = PostDetailState.Loading
                val service = RetrofitHelper.retrofit().create(PostService::class.java)
                val post = service.getPostById(id)
                post?.let {
                    _postDetailState.value = PostDetailState.Success(post)
                }?: kotlin.run {
                    _postDetailState.value = PostDetailState.PostNotFound
                }
            }.onFailure {
                _postDetailState.value = PostDetailState.Error(it)
            }
        }

    }

    fun delete(id: Int) {
        viewModelScope.launch {
            runCatching {
                _postDeleteState.emit(PostDeleteState.Loading)
                val service = RetrofitHelper.retrofit().create(PostService::class.java)
                service.delete(id)
                _postDeleteState.emit(PostDeleteState.Success)
            }.onFailure {
                _postDeleteState.emit(PostDeleteState.Error(it))
            }

            _postDeleteState.emit(PostDeleteState.Idle)
        }
    }

}