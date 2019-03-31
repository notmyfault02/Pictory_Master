package com.gram.pictory.ui.login

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class LoginViewModelFactory(private val loginNavigator: LoginNavigator) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(LoginNavigator::class.java).newInstance(loginNavigator)
    }

}