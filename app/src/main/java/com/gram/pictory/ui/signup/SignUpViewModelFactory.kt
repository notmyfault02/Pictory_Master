package com.gram.pictory.ui.signup

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class SignUpViewModelFactory(private val signUpNavigator: SignUpNavigator) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SignUpNavigator::class.java).newInstance(signUpNavigator)
    }
}