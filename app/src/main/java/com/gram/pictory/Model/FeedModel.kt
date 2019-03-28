package com.gram.pictory.Model

data class FeedModel (
    var postCode: Int,
    var user: String,
    var imgUrl: String,
    var likeCount: Int,
    var replyCount: Int,
    var postText: String
)