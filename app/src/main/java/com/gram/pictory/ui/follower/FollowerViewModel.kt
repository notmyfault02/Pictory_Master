package com.gram.pictory.ui.follower

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.gram.pictory.connect.Connecter
import com.gram.pictory.model.FollowerModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FollowerViewModel(app: Application): AndroidViewModel(app) {

    val items = MutableLiveData<ArrayList<FollowerModel>>()
    val followerPath = MutableLiveData<String>()
    val followerBool = MutableLiveData<Boolean>()
    val follwerName = MutableLiveData<String>()

    fun setAdapterData() {
        Connecter.api.getFollower("followPath")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                items.value = it
            }, {
                Log.d("FollowerVM", "error")
            })
    }

}