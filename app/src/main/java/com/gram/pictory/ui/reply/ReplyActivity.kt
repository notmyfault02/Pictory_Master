package com.gram.pictory.ui.reply

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.gram.pictory.R
import com.gram.pictory.adapter.ReplyAdapter
import com.gram.pictory.databinding.ActivityReplyBinding
import com.gram.pictory.ui.yourPage.YourPageActivity
import com.gram.pictory.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_reply.*
import org.jetbrains.anko.startActivity

class ReplyActivity: DataBindingActivity<ActivityReplyBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_reply

    private val viewModel: ReplyViewModel by lazy {
        ViewModelProviders.of(this).get(ReplyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        reply_rv.adapter = ReplyAdapter(viewModel)
        viewModel.doShowUser.observe(this, Observer { startActivity<YourPageActivity>("userPath" to viewModel.userPath.value) })
        viewModel.postCode.value = intent.getIntExtra("postCode", 0)
        viewModel.loadReplyList()
    }

}