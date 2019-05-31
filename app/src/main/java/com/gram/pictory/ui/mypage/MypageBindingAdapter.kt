package com.gram.pictory.ui.mypage

import android.arch.lifecycle.LiveData
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.gram.pictory.adapter.MyPostAdapter
import com.gram.pictory.model.MyPostModel
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("app:profileImage")
fun getProfileImage(imageView: CircleImageView, imagePath: String?) {
    Glide.with(imageView.context).load(imagePath).centerCrop().into(imageView)
}

@BindingAdapter("app:item")
fun RecyclerView.setBindItem(myPostModel: LiveData<ArrayList<MyPostModel>>) {
    val adapter: MyPostAdapter = adapter as MyPostAdapter

    myPostModel.value?.let{adapter.item = it}
}

@BindingAdapter("app:image")
fun getMyPostImage(imageView: ImageView, imagePath: String) {
    Glide.with(imageView.context)
        .load(imagePath)
        .centerCrop()
        .override(300, 300)
        .into(imageView)
}