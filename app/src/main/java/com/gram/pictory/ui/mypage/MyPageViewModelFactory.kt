package com.gram.pictory.ui.mypage

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class MyPageViewModelFactory(val contract: MyPageContract?, val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(MypageFragment::class.java).newInstance(contract, app)
}