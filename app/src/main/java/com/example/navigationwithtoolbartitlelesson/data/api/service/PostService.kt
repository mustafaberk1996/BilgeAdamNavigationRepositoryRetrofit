package com.example.navigationwithtoolbartitlelesson.data.api.service

import com.example.navigationwithtoolbartitlelesson.data.api.model.Post
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PostService {


    @GET("posts")
    suspend fun getAll():List<Post>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id:Int):Post?

    @DELETE("posts/{id}")
    suspend fun delete(@Path("id") id:Int)

    @POST("posts")
    suspend fun create(@Body post:Post):Post

    @PUT("posts/{id}")
    suspend fun update(@Body post: Post, @Path("id") id:Int):Post


}