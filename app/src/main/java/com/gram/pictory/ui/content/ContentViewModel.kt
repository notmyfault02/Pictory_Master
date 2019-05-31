package com.gram.pictory.ui.content

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.gram.pictory.connect.Connecter
import com.gram.pictory.util.SingleLiveEvent
import com.gram.pictory.util.getToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ContentViewModel(val app: Application): AndroidViewModel(app) {

    var postCode =  MutableLiveData<Int>()
    val user = MutableLiveData<String>()
    val imgURL = MutableLiveData<String>()
    val likeCheck = MutableLiveData<Boolean>()

    val doReply = SingleLiveEvent<Any>()

    fun getContent() {
        Connecter.api.getContent(getToken(app.applicationContext))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                postCode.value = it.postCode
                user.value = it.user
                imgURL.value = it.imgUrl
                likeCheck.value = it.likeCheck
            }, {
                Log.d("ContentVM", "글 불러오기 실패")
            })

    }

    fun goReply() {
        doReply.call()
    }
}