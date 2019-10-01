package com.gram.pictory.ui.yourPage

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.gram.pictory.R
import com.gram.pictory.databinding.ActivityYourPageBinding
import com.gram.pictory.util.DataBindingActivity

class YourPageActivity : DataBindingActivity<ActivityYourPageBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_your_page

    private val viewModel: YourPageViewModel by lazy {
        ViewModelProviders.of(this).get(YourPageViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel


    }
}
