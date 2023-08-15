package com.example.navigationwithtoolbartitlelesson.data.state

sealed class CreatePostState{
    object Idle:CreatePostState()
    object Loading:CreatePostState()
    object Success:CreatePostState()
    class Error(val throwable: Throwable):CreatePostState()
}
