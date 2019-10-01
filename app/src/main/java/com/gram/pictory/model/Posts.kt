package com.gram.pictory.model

data class Posts(
    var liker: List<String>,
    var imageName: String,
    var imagePath: String,
    var comments: List<String>,
    var _id: String,
    var text: String,
    var username: String,
    var id: String,
    var __v: Int,
    var postCode: Int,
    var profilePath: String
)