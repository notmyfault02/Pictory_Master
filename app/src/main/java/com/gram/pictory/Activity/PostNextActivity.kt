package com.gram.pictory.Activity

import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.gram.pictory.Connect.Connecter
import kotlinx.android.synthetic.main.activity_post_next.*
import kotlinx.android.synthetic.main.snippet_top_postbar.*
import org.jetbrains.anko.image
import org.jetbrains.anko.imageBitmap
import org.jetbrains.anko.imageURI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostNextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.gram.pictory.R.layout.activity_post_next)

        postEditText.setHorizontallyScrolling(false)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)


        val path = intent.getStringExtra("path")
        if (path.contains("noPath")) imageView2.setImageResource(com.gram.pictory.R.drawable.ic_launcher_background)
        else imageView2.imageURI = Uri.parse(path)

        imageView2.imageBitmap = (imageView2.drawable as BitmapDrawable).bitmap

        tvPrev.setOnClickListener {
            finish()
        }

        tvPost.setOnClickListener {
            Connecter.api.post(hashMapOf(
                //"id" to idSignUpEditText.text,
                "imgURL" to imageView2.image,
                "postText" to postEditText.text
            )).enqueue(object: Callback<Unit> {
                override fun onResponse(call: Call<Unit>?, response: Response<Unit>?) {
                    successPost()
                    //Toast.makeText(this, "게시물을 올렸습니다", Toast.LENGTH_SHORT).show()
                    finish()
                }

                override fun onFailure(call: Call<Unit>?, t: Throwable?) {
                    failPost()
                    //Toast.makeText(this, "게시물을 올리지 못했습니다", Toast.LENGTH_SHORT).show()
                    finish()
                }
            })
        }
    }

    fun successPost(){
        // 토큰 저장
    }

    fun failPost(){
        // 토큰
    }

}
