package com.example.archpatternandroid.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.archpatternandroid.R
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class MyFunction {

    // images
    fun displayImagePreview(ctx: Context, iv: ImageView, file: File) {
        Glide.with(ctx).load(file)
            .apply(RequestOptions().centerCrop().circleCrop()
            .placeholder(R.drawable.ic_launcher_background)).into(iv)
    }

    private val MULTIPART_FORM_DATA = "multipart/form-data"

    /**
     * Create request body for image resource
     *
     * @param file
     * @return
     */
    fun createRequestForImage(file: File): RequestBody {
        return RequestBody.create(MediaType.parse("image/*"), file)
    }

    /**
     * Create request body for file pdf resource
     *
     * @param file
     * @return
     */
    fun createRequestForFile(file: File): RequestBody {
        return RequestBody.create(MediaType.parse("application/pdf"), file)
    }

    /**
     * Create request body for string
     *
     * @param string
     * @return
     */
    fun createPartFromString(string: String): RequestBody {
        return RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), string)
    }

    /**
     * return multipart part request body
     *
     * @param filePath
     * @return
     */
    fun createMultipartBody(filePath: String): MultipartBody.Part {
        val file = File(filePath)
        val requestBody = createRequestForImage(file)
        return MultipartBody.Part.createFormData("photo", file.getName(), requestBody)
    }
}