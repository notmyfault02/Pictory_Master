package com.gram.pictory.model

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("token")
    var token: String
)