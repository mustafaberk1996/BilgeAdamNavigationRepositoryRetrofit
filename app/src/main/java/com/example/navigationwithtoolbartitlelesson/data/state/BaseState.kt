package com.example.navigationwithtoolbartitlelesson.data.state

sealed class BaseState {
    object Idle:BaseState()
    object Loading:BaseState()
    class Error(val throwable: Throwable? = null):BaseState()
}