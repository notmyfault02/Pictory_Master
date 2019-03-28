package com.gram.pictory.Model

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("accesstoken")
    var accessToken: String,
    @SerializedName("refreshtoken")
    var refreshToken: String
)