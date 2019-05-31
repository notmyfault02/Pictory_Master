package com.gram.pictory.ui.reply

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.widget.Toast
import com.gram.pictory.connect.Connecter
import com.gram.pictory.connect.Connecter.api
import com.gram.pictory.model.ReplyListModel
import com.gram.pictory.util.SingleLiveEvent
import com.gram.pictory.util.getToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReplyViewModel(app: Application): AndroidViewModel(app) {

    val items = MutableLiveData<ArrayList<ReplyListModel>>()
    val postCode = MutableLiveData<Int>()
    val userPath = MutableLiveData<String>()
    val replyText = MutableLiveData<String>()

    val doShowUser = SingleLiveEvent<Any>()

    fun setReply() {
        api.postReply(getToken(getApplication()), postCode.value!!, replyText)
            .enqueue(object: Callback<Unit> {
            override fun onResponse(call: Call<Unit>?, response: Response<Unit>?) {
                loadReplyList()
            }

            override fun onFailure(call: Call<Unit>?, t: Throwable?) {
                Toast.makeText(getApplication(),"댓글을 달 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun loadReplyList(){
        Connecter.api.getReply(getToken(getApplication()), postCode.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                items.value = it
            }, {
                Log.d("ReplyVM", "데이터를 불러오지 못함")
            })
    }

    fun goUser(index: Int){
        userPath.value = items.value!![index].userPath
        doShowUser.call()
    }

}