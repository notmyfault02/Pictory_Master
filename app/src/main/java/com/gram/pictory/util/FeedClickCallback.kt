package com.gram.pictory.util

interface FeedClickCallback{
    fun intentToReply(postCode: Int, imageUrl: String, username: String, postText: String)
}