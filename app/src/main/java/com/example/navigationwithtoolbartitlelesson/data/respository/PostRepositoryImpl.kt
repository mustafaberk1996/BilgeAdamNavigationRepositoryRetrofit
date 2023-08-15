package com.example.navigationwithtoolbartitlelesson.data.respository

import com.example.navigationwithtoolbartitlelesson.data.api.RetrofitHelper
import com.example.navigationwithtoolbartitlelesson.data.api.model.Post
import com.example.navigationwithtoolbartitlelesson.data.api.service.PostService

class PostRepositoryImpl:PostRepository {

    private val service: PostService = RetrofitHelper.retrofit().create(PostService::class.java)

    override suspend fun getPosts(): List<Post> {
        return service.getAll()
    }

    override suspend fun getPostById(id: Int): Post? {
       return service.getPostById(id)
    }

    override suspend fun delete(id: Int): Boolean {
        return runCatching {
            service.delete(id)
        }.isSuccess
    }

    override suspend fun update(post: Post): Boolean {
        return runCatching {
            val post = service.update(post, post.id)
        }.isSuccess
    }
}