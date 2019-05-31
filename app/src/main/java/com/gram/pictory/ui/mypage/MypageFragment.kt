package com.gram.pictory.ui.mypage

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import com.gram.pictory.R
import com.gram.pictory.databinding.FragmentMypageBinding
import com.gram.pictory.ui.follower.FollowerActivity
import com.gram.pictory.ui.profileEdit.ProfileEditActivity
import com.gram.pictory.util.DataBindingFragment
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.jetbrains.anko.support.v4.startActivity

class MypageFragment : DataBindingFragment<FragmentMypageBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_mypage



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(activity!!).get(MyPageViewModel::class.java)
        binding.vm = viewModel
        register(binding.vm!!)
        see_follower.setOnClickListener {
            startActivity<FollowerActivity>()
        }
        viewModel.getMypage()
        viewModel.doEditEvent.observe(this, Observer { startActivity<ProfileEditActivity>(
            "userName" to viewModel.userName.value, "profileIMG" to viewModel.profileIMG.value
        ) })
        viewModel.goFollowerListEvent.observe(this, Observer { startActivity<FollowerActivity>() })
        viewModel.goFollowingListEvent.observe(this, Observer { startActivity<FollowerActivity>() })

        fragmentManager?.beginTransaction().run {
            this!!.replace(R.id.myPageFrame, MyPostFragment())
                commit()
        }

        myPageNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

    }

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val transaction = fragmentManager?.beginTransaction()
        when (item.itemId) {
            R.id.mypost -> {
                transaction?.replace(R.id.myPageFrame, MyPostFragment())
                transaction?.commit()
                //viewModel.getMyPost()
                return@OnNavigationItemSelectedListener true
            }
            R.id.like -> {
                transaction?.replace(R.id.myPageFrame, MyLikeFragment())
                transaction?.commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.mypost -> {
                fragmentManager?.beginTransaction()?.replace(R.id.myPageFrame, MyPostFragment())?.commit()
            }
            R.id.like -> {
                fragmentManager?.beginTransaction()?.replace(R.id.myPageFrame, MyLikeFragment())?.commit()
            }
        }
        return super.onContextItemSelected(item)
    }

}