package com.gram.pictory.ui.following

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.gram.pictory.R
import com.gram.pictory.adapter.FollowingAdapter
import com.gram.pictory.databinding.ActivityFollowingBinding
import com.gram.pictory.ui.yourPage.YourPageActivity
import com.gram.pictory.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_following.*
import org.jetbrains.anko.startActivity

class FollowingActivity : DataBindingActivity<ActivityFollowingBinding>(){

    override val layoutId: Int
        get() = R.layout.activity_following

    val viewModel: FollowingViewModel by lazy {
        ViewModelProviders.of(this).get(FollowingViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        following_list_rv.adapter = FollowingAdapter(viewModel)
        viewModel.userID.value = intent.getStringExtra("userID")
        viewModel.setAdapterData()
        viewModel.doShowUser.observe(this, Observer { startActivity<YourPageActivity>("userID" to viewModel.userID) })
    }
}
