package com.example.retrofit.model

import com.google.gson.annotations.SerializedName

class User(
    val id: Int,
    val name: String,
    @SerializedName("email")
    val email_user: String,
    val username: String

)