package com.gram.pictory.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gram.pictory.databinding.ItemMypageImageBinding
import com.gram.pictory.model.MyPostModel
import com.gram.pictory.ui.mypage.MyPageViewModel

class MyPostAdapter(val viewModel: MyPageViewModel): RecyclerView.Adapter<MyPostAdapter.MyPostViewHolder>() {

    var item = arrayListOf<MyPostModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onBindViewHolder(holder: MyPostViewHolder, position: Int) {
        holder.bind()
    }
    override fun getItemCount(): Int = item.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostViewHolder {
        val binding =  ItemMypageImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPostViewHolder(binding)
    }

//    private fun decodeSampledBitmapFromURI(path: String): Bitmap? {
//        return BitmapFactory.Options().run {
//            inJustDecodeBounds = true
//            BitmapFactory.decodeFile(path, this)
//
//            inSampleSize = 300
//            inJustDecodeBounds = false
//            BitmapFactory.decodeFile(path, this)
//        }
//    }


    inner class MyPostViewHolder(private val binding: ItemMypageImageBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.vm = viewModel
            binding.myPostModel = item[adapterPosition]
        }
    }
}