package com.arifdauhi.technicaldanamon.model.user

data class Response(
    val `data`: Data?,
    val meta: Meta
)

data class ResponseUserList(
    val `data`: List<Data>,
    val meta: Meta
)