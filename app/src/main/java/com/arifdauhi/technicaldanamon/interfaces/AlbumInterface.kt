package com.arifdauhi.technicaldanamon.interfaces

import com.arifdauhi.technicaldanamon.model.album.Photos
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumInterface {
    @GET("photos")
    suspend fun get(@Query("_page") page:Int,
                    @Query("_limit") limit:Int
    ): List<Photos>
}