package com.gram.pictory.ui.mypage

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.gram.pictory.connect.Connecter
import com.gram.pictory.model.MyPostModel
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
    val imageName = MutableLiveData<ArrayList<String>>()

    val items = MutableLiveData<ArrayList<MyPostModel>>()
    val selected = MutableLiveData<MyPostModel>()

    val postPointText = MutableLiveData<String>().apply {value = "0" }
    val followingPointText = MutableLiveData<String>().apply { value = "0" }
    val followerPoiontText = MutableLiveData<String>().apply { value = "0" }

    val doEditEvent = SingleLiveEvent<Any>()
    val goFollowingListEvent = SingleLiveEvent<Any>()
    val goFollowerListEvent = SingleLiveEvent<Any>()
    val addImageEvent = SingleLiveEvent<Any>()
    val doShowContent = SingleLiveEvent<Any>()

    override fun apply(event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_RESUME -> {
                Log.d("Mypage", "resume")
                getMypage()
            }
        }
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
                imageName.value = it.imageName
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

//    fun getMyPost(): ArrayList<MyPostModel> {
//        var data = ArrayList<MyPostModel>()
//
//        Connecter.api.getMyPost(getToken(app.applicationContext))
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                data = it
//                for (i in data) {
//                    items.add(i)
//                }
//            }, {
//                Log.d("에러", "내 글을 불러오는 중 오류가 생겼습니다")
//            })
//        return data
//    }

//    fun getMyLike() {
//        Connecter.api.getMyLike(getToken(app.applicationContext))
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                items2.value = it
//            }, {
//                Log.d("에러", "내가 좋아요 누른 글 불러오기 실패")
//            })
//    }

    fun toEditProfile() = doEditEvent.call()

    fun toShowFollower() = goFollowerListEvent.call()
    fun toShowFollowing() = goFollowingListEvent.call()
    fun selectProfileImage() = addImageEvent.call()

    fun getData(st: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plane"), st)
    }

    fun goContext(index: Int) {
        selected.value = items.value!![index]
        doShowContent.call()
    }
}