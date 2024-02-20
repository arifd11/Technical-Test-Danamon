package com.arifdauhi.technicaldanamon.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifdauhi.technicaldanamon.model.album.Photos
import com.arifdauhi.technicaldanamon.model.user.Data
import com.arifdauhi.technicaldanamon.model.user.Meta
import com.arifdauhi.technicaldanamon.model.user.ResponseUserList
import com.arifdauhi.technicaldanamon.services.AlbumServices
import com.arifdauhi.technicaldanamon.services.UserServices
import com.arifdauhi.technicaldanamon.util.errorHandler
import com.arifdauhi.technicaldanamon.util.keySecret
import com.arifdauhi.technicaldanamon.util.saveLoginData
import com.arifdauhi.technicaldanamon.util.simpleToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userService: UserServices,
    private val albumServices: AlbumServices
) : ViewModel() {
    private val _users = MutableSharedFlow<ResponseUserList>()
    val users = _users.asSharedFlow()

    fun getAllUsers(id: String) {
        viewModelScope.launch {
            try {
                val response = userService.all(keySecret, id)

                if (response.meta.status && response.meta.message == "Found user data"){
                    _users.emit(response)
                }
            } catch (e:Exception){
                val error = errorHandler(e)
                val empty = ResponseUserList(emptyList(), error)
                _users.emit(empty)
            }
        }
    }

    private val _requestStatus = MutableSharedFlow<Meta>()
    val requestStatus = _requestStatus.asSharedFlow()

    private fun sendMessage(meta: Meta) {
        viewModelScope.launch {
            _requestStatus.emit(meta)
        }
    }

    fun login(key:String, body: RequestBody) {
        viewModelScope.launch {
            try {
                val login = userService.login(key, body)
                saveLoginData(login.data!!)
                sendMessage(login.meta)
            } catch (e: Exception){
                val error = errorHandler(e)
                sendMessage(error)
            }
        }
    }

    fun register(key:String, body: RequestBody) {
        viewModelScope.launch {
            try {
                val register = userService.register(key, body)
                sendMessage(register.meta)
            } catch (e: Exception){
                val error = errorHandler(e)
                sendMessage(error)
            }
        }
    }

    private val _albums = MutableSharedFlow<List<Photos>>()
    val albums = _albums.asSharedFlow()

    fun getAlbums(page: Int, limit: Int, context: Context) {
        viewModelScope.launch {
            try {
                val response = albumServices.get(page, limit)
                _albums.emit(response)
            } catch (e:Exception){
                val error = errorHandler(e)
                simpleToast(context, error.message)
            }
        }
    }
}
