package com.gram.pictory.ui.mypage

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.gram.pictory.connect.Connecter
import com.gram.pictory.model.Posts
import com.gram.pictory.model.UserModel
import com.gram.pictory.util.LifecycleCallback
import com.gram.pictory.util.SingleLiveEvent
import com.gram.pictory.util.getToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody

class MyPageViewModel(val app: Application) : AndroidViewModel(app), LifecycleCallback {

    val username = MutableLiveData<String>()
    val profileIMG = MutableLiveData<String>()
    val id = MutableLiveData<String>()
    val birth = MutableLiveData<String>()
    val imageName = MutableLiveData<List<String>>()
    val postCode = MutableLiveData<Int>()

    val items = MutableLiveData<ArrayList<Posts>>()

    val model=MutableLiveData<UserModel>()

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
                //getMypage()
            }
        }
    }
// rxjava 사용
    fun getMypage() {
        Connecter.api.getUserInfo(getToken(app.applicationContext))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("MypageVM", "" + it.user.username)
                username.value = it.user.username
                profileIMG.value = it.user.profileIMG
                id.value = it.user.id
                birth.value = it.user.birth
                imageName.value = it.user.imageName
                //postPointText.value = it.user.postPointText
                //followerPoiontText.value = it.followerCount
                //followingPointText.value = it.followingCount
            }, {
                Log.d("MyPageVM", it.message)
            })
    }
    //rxjava 미사용
//    fun getMypage() {
//        Connecter.api.getUserInfo(getToken(app.applicationContext)).enqueue(object: retrofit2.Callback<UserModel> {
//            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
//                model.value = response.body()
//
//            }
//
//            override fun onFailure(call: Call<UserModel>, t: Throwable) {
//                Log.d("mypagevm", "failed")
//            }
//        })
//        }
//    }

    fun getMyPost() {

        Connecter.api.getUserInfo(getToken(app.applicationContext))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                items.value = it.posts
                Log.d("Mypost", "성공")
            }, {
                Log.d("Mypost", "내 글을 불러오는 중 오류가 생겼습니다")
            })
    }

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

    fun toEditProfile() {
//        Connecter.api.profileEdit(
//            getToken(getApplication()),
//            getData(username.value!!),
//            getData(birth.value!!))
//            .enqueue(object: Callback<Unit> {
//                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
//                    Log.d("profileEdit", "ok")
//                }
//
//                override fun onFailure(call: Call<Unit>, t: Throwable) {
//                    Log.d("profileEdit", "error")
//                }
//            })
        doEditEvent.call()
    }

    fun toShowFollower() {
        goFollowerListEvent.call()
    }
    fun toShowFollowing() = goFollowingListEvent.call()
    fun selectProfileImage() = addImageEvent.call()

    fun getData(st: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plane"), st)
    }


    fun goContext(index: Int) {
        postCode.value = items.value!![index].postCode
        doShowContent.call()
    }
}