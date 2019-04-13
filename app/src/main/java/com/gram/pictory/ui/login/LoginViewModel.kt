package com.gram.pictory.ui.login

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.JsonObject
import com.gram.pictory.Connect.Connecter
import com.gram.pictory.Model.LoginModel
import com.gram.pictory.Util.saveToken
import com.gram.pictory.database.Auth
import com.gram.pictory.database.AuthDatabase
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(val navigator: LoginNavigator) : ViewModel() {
    val loginId = MutableLiveData<String>()
    val loginPw = MutableLiveData<String>()

    fun doLogin(view: View) {
        if (loginId.isValueBlank()) {
            Toast.makeText(view.context, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
        }
        else if(loginPw.isValueBlank()) {
            Toast.makeText(view.context, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
        }
        else {
            val auth = Auth(loginId.value!!, loginPw.value!!)
            val json = JsonObject().apply {
                addProperty("id", loginId.value)
                addProperty("pw", loginPw.value)
            }
            Connecter.api.login(json).enqueue(object : Callback<LoginModel> {
                override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                    doAsync {
                        AuthDatabase.getInstance(view.context)!!
                            .getAuthDao().insert(auth)
                    }
                    Log.d("로그인", "성공")
                    saveToken(view.context, response.body()!!.accessToken)
                    saveToken(view.context, response.body()!!.refreshToken!!, false)
                }

                override fun onFailure(call: Call<LoginModel>?, t: Throwable?) {
                    Log.d("로그인", "실패")
                    Toast.makeText(view.context, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    fun toSignUpBtn() = navigator.intentToSignUp()
    fun toMain() = navigator.intentToMain()

    fun MutableLiveData<String>.isValueBlank() = this.value.isNullOrBlank()
}