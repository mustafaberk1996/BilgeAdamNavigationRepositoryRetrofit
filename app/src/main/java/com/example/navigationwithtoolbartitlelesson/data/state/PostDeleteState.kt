package com.example.navigationwithtoolbartitlelesson.data.state

sealed class PostDeleteState {
    object Idle:PostDeleteState()
    object Success:PostDeleteState()
    object Loading:PostDeleteState()
    class Error(val throwable: Throwable? = null):PostDeleteState()
}