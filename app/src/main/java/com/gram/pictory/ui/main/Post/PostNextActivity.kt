package com.gram.pictory.ui.main.post

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import com.gram.pictory.R
import com.gram.pictory.databinding.ActivityPostNextBinding
import com.gram.pictory.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_post_next.*
import org.jetbrains.anko.imageBitmap
import org.jetbrains.anko.imageURI


class PostNextActivity : DataBindingActivity<ActivityPostNextBinding>(){

    override val layoutId: Int
        get() = R.layout.activity_post_next

    private val viewModel: PostViewModel by lazy {
        ViewModelProviders.of(this).get(PostViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = viewModel

        viewModel.doPrevEvent.observe(this, Observer { finish() })

        postnext_text_tv.setHorizontallyScrolling(false)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)


        val path = intent.getStringExtra("path")

        if (path.contains("noPath")) postnext_image_imv.setImageResource(R.drawable.ic_launcher_background)
        else postnext_image_imv.imageURI = Uri.parse(path)
        viewModel.imageName.value = path
        postnext_image_imv.imageBitmap = (postnext_image_imv.drawable as BitmapDrawable).bitmap

    }

}
