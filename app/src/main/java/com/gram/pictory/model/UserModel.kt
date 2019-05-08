package com.gram.pictory.model

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("userName")
    var userName: String
)
