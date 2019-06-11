package com.gram.pictory.ui.mypage

import android.arch.lifecycle.LiveData
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.gram.pictory.adapter.MyPostAdapter
import com.gram.pictory.model.Posts
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("app:profileImage")
fun getProfileImage(imageView: CircleImageView, imagePath: String?) {
    Glide.with(imageView.context).load(imagePath).centerCrop().override(300,300).into(imageView)
}

@BindingAdapter("app:mypostItem")
fun RecyclerView.setBindItem(model: LiveData<ArrayList<Posts>>) {
    val adapter: MyPostAdapter = adapter as MyPostAdapter
    model.value?.let{adapter.item = it}
}

@BindingAdapter("app:mypostThumbnail")
fun getMyPostImage(imageView: ImageView, imagePath: String) {
    Glide.with(imageView.context)
        .load(imagePath)
        .centerCrop()
        .override(300, 300)
        .into(imageView)
}