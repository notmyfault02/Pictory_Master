package com.gram.pictory.ui.signup

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import android.widget.Toast
import com.google.gson.JsonObject
import com.gram.pictory.Connect.Connecter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel(val signUpNavigator: SignUpNavigator) : ViewModel() {
    val registerName = MutableLiveData<String>()
    val registerId = MutableLiveData<String>()
    val registerPw = MutableLiveData<String>()
    val registerBirth = MutableLiveData<String>()

    fun doSignUp(view: View) {
        if (registerName.isValueBlank() || registerId.isValueBlank()
            || registerPw.isValueBlank()|| registerBirth.isValueBlank()) {
            Toast.makeText(view.context,"정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else {
            val json = JsonObject().apply {
                addProperty("name", registerName.value)
                addProperty("id", registerId.value)
                addProperty("password", registerPw.value)
            }
            Connecter.api.signUp(json).enqueue(object: Callback<Unit> {
                override fun onResponse(call: Call<Unit>?, response: Response<Unit>?) {
                    signUpNavigator.intentToLogin()
                }

                override fun onFailure(call: Call<Unit>?, t: Throwable?) {
                    signUpNavigator.intentToLogin()
                }
            })
        }
    }

    fun MutableLiveData<String>.isValueBlank() =
        this.value.isNullOrBlank()
}