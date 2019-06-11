package com.gram.pictory.ui.mypage

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private val viewModel: MyPageViewModel by lazy {
        ViewModelProviders.of(this).get(MyPageViewModel::class.java)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.vm = viewModel
        mypost_contentlist_rv.adapter = MyPostAdapter(viewModel)
        viewModel.getMyPost()
        viewModel.doShowContent.observe(this, Observer { startActivity<ContentActivity>("postCode" to viewModel.postCode.value)})
        return inflater.inflate(R.layout.fragment_my_post, container, false)
    }

}
