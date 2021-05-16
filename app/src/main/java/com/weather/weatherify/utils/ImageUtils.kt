package com.weather.weatherify.utils

import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources

import com.bumptech.glide.Glide
import com.weather.weatherify.R
import java.io.File


fun ImageView.getBackgroundImage(backgroundUrl: Uri?) {

    if (backgroundUrl != null) {


        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

        val cursor: Cursor? = this.context?.contentResolver?.query(
            backgroundUrl, filePathColumn, null, null, null
        )
        cursor?.moveToFirst()

        val columnIndex: Int? = cursor?.getColumnIndex(filePathColumn[0])
        val filePath: String? = columnIndex?.let { cursor?.getString(it) }
        if (cursor != null) {
            cursor.close()
        }


        if(filePath!=null){
        this.context?.let {
            Glide.with(it)
                .load(File(filePath)).into(this)
        }
        }
        else{
            this.context?.let {
                Glide.with(it)
                    .load(  AppCompatResources.getDrawable(
                        it,
                        R.drawable.less_cloud_evening
                    )).into(this)
            }
        }


    }
}
