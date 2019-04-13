package com.gram.pictory.ui.mypage

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.view.*
import com.gram.pictory.R
import com.gram.pictory.Util.DataBindingFragment
import com.gram.pictory.databinding.FragmentMypageBinding
import com.gram.pictory.ui.FollowerActivity
import com.gram.pictory.ui.ProfileEditActivity
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.jetbrains.anko.support.v4.startActivity

class MypageFragment : DataBindingFragment<FragmentMypageBinding>(), MyPageContract {

    override val layoutId: Int
        get() = com.gram.pictory.R.layout.fragment_mypage

    private val fm: FragmentManager? by lazy { fragmentManager }
  
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val factory = MyPageViewModelFactory(this, activity!!.application)

        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        see_follower.setOnClickListener {
            startActivity<FollowerActivity>()
        }

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

    override fun goToEditProfile() {
        startActivity<ProfileEditActivity>()
    }

}