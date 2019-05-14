package com.gram.pictory.ui.login

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.widget.Toast
import com.google.gson.JsonObject
import com.gram.pictory.connect.Connecter
import com.gram.pictory.database.Auth
import com.gram.pictory.database.AuthDatabase
import com.gram.pictory.model.LoginModel
import com.gram.pictory.util.SingleLiveEvent
import com.gram.pictory.util.saveToken
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(val app: Application) : AndroidViewModel(app) {
    val loginId = MutableLiveData<String>()
    val loginPw = MutableLiveData<String>()

    val goMainEvent = SingleLiveEvent<Any>()
    val goRegisterEvent = SingleLiveEvent<Any>()

    fun doLogin() {
        if (loginId.isValueBlank()) {
            Toast.makeText(app.baseContext, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
        }
        else if(loginPw.isValueBlank()) {
            Toast.makeText(app.baseContext, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
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
                        AuthDatabase.getInstance(app.baseContext)!!
                            .getAuthDao().insert(auth)
                    }
                    saveToken(app.baseContext, response.body()!!.token)
                    toMain()
                }

                override fun onFailure(call: Call<LoginModel>?, t: Throwable?) {
                    Toast.makeText(app.baseContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    fun toSignUpBtn() = goRegisterEvent.call()
    fun toMain() = goMainEvent.call()

    fun MutableLiveData<String>.isValueBlank() = this.value.isNullOrBlank()
}