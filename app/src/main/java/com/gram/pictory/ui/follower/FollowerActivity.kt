package com.gram.pictory.ui.follower

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.gram.pictory.R
import com.gram.pictory.adapter.FollowerAdapter
import com.gram.pictory.databinding.ActivityFollwerBinding
import com.gram.pictory.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_follwer.*

class FollowerActivity: DataBindingActivity<ActivityFollwerBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_follwer

    private val viewModel: FollowerViewModel by lazy {
        ViewModelProviders.of(this).get(FollowerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = viewModel
        follower_list_rv.adapter = FollowerAdapter(viewModel)
        viewModel.userID.value = intent.getStringExtra("id")
        viewModel.setAdapterData()
    }

}