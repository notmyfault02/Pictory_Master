package com.gram.pictory.ui.content

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.gram.pictory.R
import com.gram.pictory.databinding.ActivityContentBinding
import com.gram.pictory.ui.YourPage.YourPageActivity
import com.gram.pictory.ui.reply.ReplyActivity
import com.gram.pictory.util.DataBindingActivity
import org.jetbrains.anko.startActivity

class ContentActivity : DataBindingActivity<ActivityContentBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_content

    private val viewModel: ContentViewModel by lazy {
        ViewModelProviders.of(this).get(ContentViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        //viewModel.postCode = intent.data("selected")
        viewModel.getContent()
        viewModel.doReply.observe(this, Observer { startActivity<ReplyActivity>("postCode" to viewModel.postCode.value) })
        viewModel.doUserInfo.observe(this, Observer { startActivity<YourPageActivity>("userID" to viewModel.userID.value) })
    }
}
