package com.gram.pictory.model

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("username")
    var username: String,
    @SerializedName ("id")
    var id: String,
    @SerializedName("pw")
    var pw: String,
    @SerializedName ("birth")
    var birth: String
)
