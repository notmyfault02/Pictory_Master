package com.gram.pictory.ui.mypage

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.gram.pictory.connect.Connecter
import com.gram.pictory.util.LifecycleCallback
import com.gram.pictory.util.SingleLiveEvent
import com.gram.pictory.util.getToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody

class MyPageViewModel(val app: Application) : AndroidViewModel(app), LifecycleCallback {

    val userName = MutableLiveData<String>()
    val profileIMG = MutableLiveData<String>()
    val id = MutableLiveData<String>()
    val birth = MutableLiveData<String>()

    val postPointText = MutableLiveData<String>().apply {value = "0" }
    val followingPointText = MutableLiveData<String>().apply { value = "0" }
    val followerPoiontText = MutableLiveData<String>().apply { value = "0" }

    val doEditEvent = SingleLiveEvent<Any>()
    val goFollowingListEvent = SingleLiveEvent<Any>()
    val goFollowerListEvent = SingleLiveEvent<Any>()
    val addImageEvent = SingleLiveEvent<Any>()

    override fun apply(event: Lifecycle.Event) {
//        when(event) {
//            Lifecycle.Event.ON_RESUME -> {
//                Log.d("Mypage", "resume")
//                //getMypage()
//                Connecter.api.getUserInfo(getToken(app.applicationContext))
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({
//                        userName.value = it.username
//                        profileIMG.value = it.myfile
//                        id.value = it.id
//                        birth.value = it.birth
//
//                    }, {
//                        Log.d("MyPageVM", "getMyPage Failed")
//                    })
//            }
//        }
    }

    fun getMypage() {
        Connecter.api.getUserInfo(getToken(app.applicationContext))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userName.value = it.username
                profileIMG.value = it.myfile
                id.value = it.id
                birth.value = it.birth

            }, {
                Log.d("MyPageVM", "getMyPage Failed")
            })
    }

    fun doEditProfile() {
        Connecter.api.profileEdit(
            getToken(app.applicationContext),
            getData(userName.value!!),
            getData(id.value!!),
            getData(birth.value!!)
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                Log.d("프로필수정", "성공")
            }, {
                Log.d("프로필수정", "실패")
            })

    }

    fun toEditProfile() = doEditEvent.call()
    fun toShowFollower() = goFollowerListEvent.call()
    fun toShowFollowing() = goFollowingListEvent.call()
    fun selectProfileImage() = addImageEvent.call()

    fun getData(st: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plane"), st)
    }
}