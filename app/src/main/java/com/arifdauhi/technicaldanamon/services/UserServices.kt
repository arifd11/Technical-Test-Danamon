package com.arifdauhi.technicaldanamon.services

import com.arifdauhi.technicaldanamon.interfaces.UserInterface
import com.arifdauhi.technicaldanamon.model.user.Response
import com.arifdauhi.technicaldanamon.model.user.ResponseUserList
import okhttp3.RequestBody
import javax.inject.Inject

class UserServices @Inject constructor(
    private val api: UserInterface
) {
    suspend fun all(key:String, id:String): ResponseUserList {
        return api.all(key, id)
    }

    suspend fun login(key: String, body: RequestBody): Response {
        return api.login(key, body)
    }

    suspend fun register(key: String, body: RequestBody): Response {
        return api.register(key, body)
    }
}