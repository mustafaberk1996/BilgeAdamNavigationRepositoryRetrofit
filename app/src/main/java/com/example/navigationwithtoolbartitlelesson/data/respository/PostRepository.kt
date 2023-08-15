package com.example.navigationwithtoolbartitlelesson.data.respository

import com.example.navigationwithtoolbartitlelesson.data.api.model.Post

interface PostRepository {

    suspend fun getPosts():List<Post>

    suspend fun getPostById(id:Int):Post?

    suspend fun delete(id:Int):Boolean

    suspend fun update(post: Post):Boolean
}