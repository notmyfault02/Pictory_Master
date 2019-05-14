package com.gram.pictory.ui.main.post

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.gram.pictory.connect.Connecter
import com.gram.pictory.util.SingleLiveEvent
import com.gram.pictory.util.getToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel(val app: Application): AndroidViewModel(app){
    val postEditTextView = MutableLiveData<String>()

    val doPrevEvent = SingleLiveEvent<Any>()

    fun post() {
        val map = hashMapOf(
            "postText" to postEditTextView.value
        )

        Connecter.api.post(getToken(app.applicationContext), map).enqueue(object: Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {

            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {

            }
        })
    }

    fun doPrev() = doPrevEvent.call()
}