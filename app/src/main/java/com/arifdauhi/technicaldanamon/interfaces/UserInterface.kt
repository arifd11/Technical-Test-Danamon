package com.arifdauhi.technicaldanamon.interfaces

import com.arifdauhi.technicaldanamon.model.user.Response
import com.arifdauhi.technicaldanamon.model.user.ResponseUserList
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface UserInterface {
    @GET("api/user")
    suspend fun all(@Header("X-API-Key") key: String, @Query("id") id:String): ResponseUserList

    @POST("api/user/login")
    suspend fun login(@Header("X-API-Key") key: String, @Body requestBody: RequestBody): Response

    @POST("api/user/register")
    suspend fun register(@Header("X-API-Key") key: String, @Body requestBody: RequestBody): Response
}