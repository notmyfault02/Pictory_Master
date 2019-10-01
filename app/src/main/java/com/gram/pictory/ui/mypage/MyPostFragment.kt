package com.gram.pictory.ui.mypage

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.gram.pictory.R
import com.gram.pictory.adapter.MyPostAdapter
import com.gram.pictory.databinding.FragmentMyPostBinding
import com.gram.pictory.ui.content.ContentActivity
import com.gram.pictory.util.DataBindingFragment
import kotlinx.android.synthetic.main.fragment_my_post.*
import org.jetbrains.anko.support.v4.startActivity

class MyPostFragment : DataBindingFragment<FragmentMyPostBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_my_post


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(activity!!).get(MyPageViewModel::class.java)
        binding.vm = viewModel
        myPost_rv.adapter = MyPostAdapter(viewModel)
        viewModel.getMypage()
        viewModel.doShowContent.observe(this, Observer { startActivity<ContentActivity>("_id" to viewModel._id.value)})
    }


}
