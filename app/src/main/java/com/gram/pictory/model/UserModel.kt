package com.gram.pictory.model

//data class UserModel (
//    var username: String,
//    var id: String,
//    var birth: String,
//    var myfile: String,
//    var active: Boolean,
//    var profileIMG: String,
//    var imageName: ArrayList<String>,
//    var postCount: String,
//    var followingCount: String,
//    var followerCount: String
//)

data class UserModel (
    val posts: ArrayList<Posts>,
    val user: User
)
