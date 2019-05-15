package com.gram.pictory.ui.mypage

import android.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("app:profileImage")
fun getProfileImage(imageView: CircleImageView, imagePath: String) {
    Glide.with(imageView.context).load(imagePath).centerCrop().into(imageView)
}