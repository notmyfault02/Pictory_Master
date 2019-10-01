package com.gram.pictory.ui.yourPage

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData

class YourPageViewModel(app:Application): AndroidViewModel(app) {
    val profileIMG = MutableLiveData<String>()

}