package com.arifdauhi.technicaldanamon.util

import com.arifdauhi.technicaldanamon.model.user.Data
import io.paperdb.Paper

fun saveLoginData(data: Data) {
    Paper.book().write("loginData", data)
}

fun getLoginData(): Data {
    var default = Data("",-1,"","","")
    val loginData = Paper.book().read<Data>("loginData")
    if (loginData != null){
        default = loginData
    }
    return default
}

fun destroy() {
    Paper.book().destroy()
}