package com.gram.pictory.ui.reply

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.gram.pictory.connect.Connecter
import com.gram.pictory.connect.Connecter.api
import com.gram.pictory.model.ReplyListModel
import com.gram.pictory.util.SingleLiveEvent
import com.gram.pictory.util.getToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody

class ReplyViewModel(app: Application): AndroidViewModel(app) {

    val items = MutableLiveData<ArrayList<ReplyListModel>>()
    val postCode = MutableLiveData<Int>()
    val userPath = MutableLiveData<String>()
    val replyText = MutableLiveData<String>()
    val _id = MutableLiveData<String>()

    val doShowUser = SingleLiveEvent<Any>()

    fun setReply() {
        api.postReply(getToken(getApplication()), _id.value!!, getData(replyText.value!!))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {

            }, {

            })

    }


    fun loadReplyList() {
        Connecter.api.getReply(getToken(getApplication()), _id.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                items.value = it
            }, {
                Log.d("ReplyVM", it.message)
            })
    }

    fun goUser(index: Int){
        userPath.value = items.value!![index].userPath
        doShowUser.call()
    }

    fun getData(st: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plane"), st)
    }

}