package com.example.archpatternandroid.images

import android.net.Uri
import java.io.File

interface ImagesContract {

    interface Presenter {

        fun takePhotos()
        fun selectPhotos()
        fun showPreview(file: File)
        fun savePhotos(filePath: String)
        fun getPhotos(): String
    }

    interface View {
        fun openSettings()
        fun onShowPermissionDialog(isGallery: Boolean)
        fun takePhotosView(file: File?)
        fun selectPhotosView()
        fun onShowPhotosPreview(file: File)
        fun onShowDialogSelectedPhotos()
        fun onShowErrorDialog()
        fun getRealPathFromUri(uri: Uri): String
        fun newFile(): File?
        fun getFilePath() : File?
    }
}