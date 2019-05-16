package com.gram.pictory.ui.signup

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.widget.Toast
import com.gram.pictory.connect.Connecter
import com.gram.pictory.util.SingleLiveEvent
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class SignUpViewModel(val app: Application) : AndroidViewModel(app) {

    val imagePath = MutableLiveData<String>()

    val registerName = MutableLiveData<String>()
    val registerId = MutableLiveData<String>()
    val registerPw = MutableLiveData<String>()
    val registerBirth = MutableLiveData<String>()

    val doLoginEvent = SingleLiveEvent<Any>()
    val addImageEvent = SingleLiveEvent<Any>()

    fun doSignUp() {
        val file = File(imagePath.value)

        val requestBody=RequestBody.create(MediaType.parse("image/*"),file)
        val filePart = MultipartBody.Part.createFormData("profileIMG", file.name, requestBody)

        Log.i("test", filePart.body().toString())
        Log.d("signup", "$file")
        if (registerName.isValueBlank() || registerId.isValueBlank()
            || registerPw.isValueBlank()|| registerBirth.isValueBlank()) {
            Toast.makeText(app.baseContext,"정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else {
            Connecter.api.signUp(filePart, getData(registerName.value!!), getData(registerId.value!!), getData(registerPw.value!!), getData(registerBirth.value!!))
                .enqueue(object: Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Log.d("회원가입", "성공")
                    Log.d("비밀번호", registerPw.value)
                    Toast.makeText(app.baseContext, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    doLogin()
                }

                override fun onFailure(call: Call<Unit>?, t: Throwable?) {
                    Log.d("회원가입", "실패")
                    Toast.makeText(app.baseContext, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    doLogin()
                }
            })
        }
    }


    fun MutableLiveData<String>.isValueBlank() =
        this.value.isNullOrBlank()

    fun selectProfileImage() = addImageEvent.call()

    fun doLogin() = doLoginEvent.call()

    fun getData(st: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plane"), st)
    }
}
