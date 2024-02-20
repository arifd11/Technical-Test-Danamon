package com.arifdauhi.technicaldanamon.util

import android.content.Context
import android.widget.Toast
import com.arifdauhi.technicaldanamon.BuildConfig
import com.arifdauhi.technicaldanamon.model.user.Meta
import org.json.JSONObject
import retrofit2.HttpException

const val keySecret = BuildConfig.X_KEY_SECRET

fun simpleToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun errorHandler(e: Exception): Meta {
    var errorMeta = Meta(code = -1, message = "Error: ${e.message}", status = false)
    if (e is HttpException){
        val errResponseBody = JSONObject(e.response()!!.errorBody()!!.string())
        errorMeta = Meta(code = e.code(), message = errResponseBody.getJSONObject("meta").getString("message"), status = false)
    }
    return errorMeta
}