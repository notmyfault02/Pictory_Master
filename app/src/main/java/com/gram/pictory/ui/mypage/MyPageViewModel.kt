package com.gram.pictory.ui.mypage

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import com.gram.pictory.connect.Connecter
import com.gram.pictory.model.UserModel
import com.gram.pictory.util.LifecycleCallback
import com.gram.pictory.util.SingleLiveEvent
import com.gram.pictory.util.getToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageViewModel(val app: Application) : AndroidViewModel(app), LifecycleCallback {

    val userName = MutableLiveData<String>()
    val doEditEvent = SingleLiveEvent<Any>()

    override fun apply(event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_RESUME -> {
                Connecter.api.getUserInfo(getToken(app.applicationContext), Unit).enqueue(object: Callback<UserModel> {
                    override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                        userName.value = response.body()!!.username
                    }

                    override fun onFailure(call: Call<UserModel>, t: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                })

            }
        }
    }

    fun toEditProfile() = doEditEvent.call()
}