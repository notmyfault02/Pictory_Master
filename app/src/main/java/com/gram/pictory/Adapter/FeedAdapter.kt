package com.gram.pictory.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.gram.pictory.Model.FeedModel
import com.gram.pictory.R
import com.gram.pictory.Util.FeedClickCallback
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class FeedAdapter(val models: ArrayList<FeedModel>, val callback: FeedClickCallback): RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FeedViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_feed_recyclerview, p0, false)
        return FeedViewHolder(view)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(p0: FeedViewHolder, p1: Int) = p0.bind(models[p1])

    inner class FeedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val user = itemView.find<TextView>(R.id.userTextview)
        val postImg = itemView.find<ImageView>(R.id.story_img)
        val likeCount = itemView.find<TextView>(R.id.like_count)
        val replyCount = itemView.find<TextView>(R.id.reply_count)
        val postText = itemView.find<TextView>(R.id.post_text)

        fun bind(model: FeedModel) {
            user.text = model.user
            likeCount.text = model.likeCount.toString()
            replyCount.text = model.replyCount.toString()
            postText.text = model.postText

            Glide.with(postImg)
                .load(model.imgUrl)
                .into(postImg)

            postText.onClick {
                callback.intentToReply(model.postCode, model.imgUrl, model.user, model.postText)
            }
        }
    }
}