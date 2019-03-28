package com.gram.pictory.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gram.pictory.Activity.FollowerActivity
import com.gram.pictory.Activity.ProfileEditActivity
import com.gram.pictory.R
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.jetbrains.anko.support.v4.startActivity

class MypageFragment : Fragment() {
  
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_mypage, container, false)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button2.setOnClickListener {
            startActivity<ProfileEditActivity>()
        }

        see_follower.setOnClickListener {
            startActivity<FollowerActivity>()
        }

//        fragmentManager?.beginTransaction().run {
//            this!!.replace(R.id.myPageFrame, MyPostFragment())
//                commit()
//        }

        //myPageNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
    }

//    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        val transaction = fragmentManager?.beginTransaction()
//        when (item.itemId) {
//            R.id.mypost -> {
//                transaction?.replace(R.id.myPageFrame, MyPostFragment())
//                transaction?.commit()
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.like -> {
//                transaction?.replace(R.id.myPageFrame, MyLikeFragment())
//                transaction?.commit()
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }

//    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
//        super.onCreateContextMenu(menu, v, menuInfo)
//    }
//
//    override fun onContextItemSelected(item: MenuItem?): Boolean {
//        when(item?.itemId) {
//            R.id.mypost -> {
//                fragmentManager?.beginTransaction()?.replace(R.id.myPageFrame, MyPostFragment())?.commit()
//            }
//            R.id.like -> {
//                fragmentManager?.beginTransaction()?.replace(R.id.myPageFrame, MyLikeFragment())?.commit()
//            }
//        }
//        return super.onContextItemSelected(item)
//    }


}