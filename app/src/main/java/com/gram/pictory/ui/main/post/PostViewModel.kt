package com.gram.pictory.ui.main.post

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import com.gram.pictory.util.LifecycleCallback
import com.gram.pictory.util.getToken
import com.gram.pictory.connect.Connecter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel(val constract: PostConstract, val app: Application): AndroidViewModel(app), LifecycleCallback {
    val postEditTextView = MutableLiveData<String>()

    override fun apply(event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_RESUME -> {

            }
        }
    }

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
}