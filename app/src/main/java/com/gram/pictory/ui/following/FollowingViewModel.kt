package com.gram.pictory.ui.following

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.gram.pictory.connect.Connecter
import com.gram.pictory.model.FollowerModel
import com.gram.pictory.util.SingleLiveEvent
import com.gram.pictory.util.getToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FollowingViewModel(app: Application): AndroidViewModel(app) {

    val items = MutableLiveData<ArrayList<FollowerModel>>()
    val userID = MutableLiveData<String>()
    val followingPath = MutableLiveData<String>()
    val followingName = MutableLiveData<String>()

    val doShowUser = SingleLiveEvent<Any>()

    fun setAdapterData() {
        Connecter.api.getFollowing(getToken(getApplication()), userID.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                items.value = it
            }, {
                Log.d("FollowingVM", "팔로잉 불러오기 실패")
            })
    }

    fun goUserPage(index: Int) {
        userID.value = items.value!![index].userID
        doShowUser.call()
    }
}

