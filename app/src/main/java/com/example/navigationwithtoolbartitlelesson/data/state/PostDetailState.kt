package com.example.navigationwithtoolbartitlelesson.data.state

import com.example.navigationwithtoolbartitlelesson.data.api.model.Post

sealed class PostDetailState {
    object Idle:PostDetailState()
    object Loading:PostDetailState()
    object PostNotFound:PostDetailState()
    class Success(val post: Post):PostDetailState()
    class Error(val throwable: Throwable):PostDetailState()

}