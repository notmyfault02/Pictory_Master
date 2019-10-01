package com.gram.pictory.ui.yourPage

import android.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("image")
fun setProflieImage(imageView: CircleImageView, imagePath: String) {
    Glide.with(imageView.context).load(imagePath).centerCrop().into(imageView)
}