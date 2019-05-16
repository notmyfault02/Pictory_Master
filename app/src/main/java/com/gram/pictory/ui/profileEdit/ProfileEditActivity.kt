package com.gram.pictory.ui.profileEdit

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import com.gram.pictory.R
import com.gram.pictory.databinding.ActivityProfileEditBinding
import com.gram.pictory.ui.mypage.MyPageViewModel
import com.gram.pictory.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_signup.*

class ProfileEditActivity: DataBindingActivity<ActivityProfileEditBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_profile_edit

    private val viewModel: MyPageViewModel by lazy {
        ViewModelProviders.of(this).get(MyPageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        viewModel.addImageEvent.observe(this, Observer {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 1) })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1 && data != null) {
            val path = data.data
            if (resultCode === Activity.RESULT_OK) {
                try {
                    viewModel.profileIMG.value = getRealPathFromURI(path)
                    val inGallery = contentResolver.openInputStream(path)
                    val img = BitmapFactory.decodeStream(inGallery)
                    inGallery!!.close()
                    profileImage.setImageBitmap(img)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun getRealPathFromURI(contentUri: Uri): String {
        if (contentUri.getPath().startsWith("/storage")) {
            return contentUri.getPath();
        }
        val id = DocumentsContract.getDocumentId(contentUri).split(":")[1];
        val columns = arrayOf(MediaStore.Files.FileColumns.DATA)
        val selection = MediaStore.Files.FileColumns._ID + " = " + id;
        val cursor = getContentResolver().query(MediaStore.Files.getContentUri("external"), columns, selection, null, null);
        try {
            val columnIndex = cursor.getColumnIndex(columns[0])
            if (cursor.moveToFirst()) {
                return cursor.getString(columnIndex)
            }
        } finally {
            cursor.close()
        }
        return "hello"
    }


}