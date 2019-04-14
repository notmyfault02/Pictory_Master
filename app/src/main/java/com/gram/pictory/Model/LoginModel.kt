package com.gram.pictory.Model

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("token")
    var token: String
)