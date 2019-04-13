package com.gram.pictory.ui.mypage

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import com.gram.pictory.Util.LifecycleCallback

class MyPageViewModel(val contract: MyPageContract, val app: Application) : AndroidViewModel(app), LifecycleCallback {

    val nameText = MutableLiveData<String>()

    override fun apply(event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_RESUME -> {

            }
        }
    }

    fun toEditProfile() = contract.goToEditProfile()
}