package com.gram.pictory.ui.mypage

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.gram.pictory.connect.Connecter
import com.gram.pictory.model.UserModel
import com.gram.pictory.util.LifecycleCallback
import com.gram.pictory.util.SingleLiveEvent
import com.gram.pictory.util.getToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MyPageViewModel(val app: Application) : AndroidViewModel(app), LifecycleCallback {

    val userName = MutableLiveData<String>()
    val doEditEvent = SingleLiveEvent<Any>()

    override fun apply(event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_RESUME -> {
                getMypage()
            }
        }
    }

    fun getMypage(): UserModel {
        var data =  UserModel("", "", "", "")

        Connecter.api.getUserInfo(getToken(app.applicationContext), Unit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                data = it
            }, {
                Log.d("MyPageVM", "getMyPage Failed")
            })
        return data
    }

    fun toEditProfile() = doEditEvent.call()
}