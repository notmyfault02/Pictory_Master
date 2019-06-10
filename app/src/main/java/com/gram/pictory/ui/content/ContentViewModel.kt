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
import retrofit2.Call
import retrofit2.Response

class ContentViewModel(val app: Application): AndroidViewModel(app) {

    var postCode =  MutableLiveData<Int>()
    val username = MutableLiveData<String>()
    val id = MutableLiveData<String>()
    val imageName = MutableLiveData<String>()
    val likeCheck = MutableLiveData<Boolean>()
    val text = MutableLiveData<String>()
    val replyText = MutableLiveData<String>()

    val doReply = SingleLiveEvent<Any>()
    val doUserInfo = SingleLiveEvent<Any>()

    fun getContent() {
        Connecter.api.getContent(getToken(app.applicationContext))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                postCode.value = it.postCode
                username.value = it.username
                imageName.value = it.imageName
                text.value = it.text
                likeCheck.value = it.likeCheck
                id.value = it.id
            }, {
                Log.d("ContentVM", "글 불러오기 실패")
            })

    }

    fun postReply() {
        Connecter.api.postReply(getToken(getApplication()), postCode.value!!, replyText)
            .enqueue(object: retrofit2.Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Log.d("ContentVM", "댓글 달기 성공")
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("ContentVM", "댓글 달기 실패")
                }
            })
    }

    fun goUserPage() {
        doUserInfo.call()
    }

    fun goReply() {
        doReply.call()
    }

}