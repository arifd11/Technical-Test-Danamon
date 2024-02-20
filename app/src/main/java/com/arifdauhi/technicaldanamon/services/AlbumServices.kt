package com.arifdauhi.technicaldanamon.services

import com.arifdauhi.technicaldanamon.interfaces.AlbumInterface
import com.arifdauhi.technicaldanamon.model.album.Photos
import com.arifdauhi.technicaldanamon.model.user.ResponseUserList
import javax.inject.Inject

class AlbumServices @Inject constructor(
    private val api: AlbumInterface
) {
    suspend fun get(page:Int, limit: Int): List<Photos> {
        return api.get(page, limit)
    }
}