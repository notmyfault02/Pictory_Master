package com.gram.pictory.ui.main.post

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.widget.Toast
import com.gram.pictory.connect.Connecter
import com.gram.pictory.util.SingleLiveEvent
import com.gram.pictory.util.getToken
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class PostViewModel(val app: Application): AndroidViewModel(app){
    val caption = MutableLiveData<String>()
    val imagePath = MutableLiveData<String>()

    val doPrevEvent = SingleLiveEvent<Any>()

    fun post() {
        val file = File(imagePath.value)

        val requestBody=RequestBody.create(MediaType.parse("image/*"),file)
        val filePart = MultipartBody.Part.createFormData("postIMG", file.name, requestBody)
        Log.d("postViewModel", imagePath.value)
        if (caption.value.isNullOrBlank()) {
            Toast.makeText(app.baseContext, "내용을 입력해주세요", Toast.LENGTH_SHORT).show()
        } else {
            val requestText = getData(caption.value!!)
            Connecter.api.post(getToken(app.applicationContext), filePart, requestText)
                .enqueue(object: Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        Log.d("postViewModel", "성공")
                        doPrev()
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        Log.d("postViewModel", "실패")
                        doPrev()
                    }
                })
        }
    }

    fun doPrev() = doPrevEvent.call()

    fun getData(st: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plane"), st)
    }

}