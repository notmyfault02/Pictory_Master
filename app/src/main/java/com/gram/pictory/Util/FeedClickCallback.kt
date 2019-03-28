package com.gram.pictory.Util

interface FeedClickCallback{
    fun intentToReply(postCode: Int, imageUrl: String, username: String, postText: String)
}