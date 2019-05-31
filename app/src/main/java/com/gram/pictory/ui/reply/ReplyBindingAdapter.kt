package com.gram.pictory.ui.reply

import android.arch.lifecycle.LiveData
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.gram.pictory.adapter.ReplyAdapter
import com.gram.pictory.model.ReplyListModel
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("circleImage")
fun setCircleImage(imageView: CircleImageView, imagePath: String?) {
    Glide.with(imageView.context).load(imagePath).centerCrop().into(imageView)
}

@BindingAdapter("image")
fun setImage(imageView: ImageView, imagePath: String?) {
    Glide.with(imageView.context).load(imagePath).centerCrop().into(imageView)
}


@BindingAdapter("bindItems")
fun RecyclerView.setBindItems(model: LiveData<ArrayList<ReplyListModel>>) {
    var adapter: ReplyAdapter = adapter as ReplyAdapter
    model.value?.let { adapter.items = it }
}