package com.gram.pictory.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.gram.pictory.R
import com.gram.pictory.ui.main.post.PostNextActivity
import kotlinx.android.synthetic.main.item_photo.view.*

class PostAdapter(private val context: Context,
                  private val albumList: ArrayList<String>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

//    var albumList = arrayListOf<String>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    private fun decodeSampledBitmapFromURI(path: String) : Bitmap? {
        return BitmapFactory.Options().run {
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(path, this)

            inSampleSize = 300
            inJustDecodeBounds = false
            BitmapFactory.decodeFile(path, this)
        }
    }

    override fun onBindViewHolder(holder: PostViewHolder,  position: Int) {
        val path = albumList[position]
        val img = decodeSampledBitmapFromURI(albumList[position])

        if (img != null) {
            Glide.with(context)
                .load(path).override(300, 300)
                .into(holder.itemView.photo)
        }
        else holder.itemView.photo.setImageResource(R.drawable.ic_launcher_background)

        holder.itemView.photo.setOnClickListener {
            val intent = Intent(context, PostNextActivity::class.java)
            if(img != null) intent.putExtra("path", albumList[position])
            else intent.putExtra("path", "noPath")
            context.startActivity(intent)
        }
    }

    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount() = albumList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder = PostViewHolder(parent)

    class PostViewHolder(parentView: View) : RecyclerView.ViewHolder(
        LayoutInflater.from(parentView.context).inflate(R.layout.item_photo, null, false))
}