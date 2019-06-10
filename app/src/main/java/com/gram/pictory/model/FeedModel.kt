package com.gram.pictory.model

data class FeedModel (
    var postCode: Int,
    var username: String,
    var id: String,
    var text: String,
    var imageName: String,
    var likeCount: Int,
    var likeCheck: Boolean,
    var replyCount: Int,
    var postText: String
)