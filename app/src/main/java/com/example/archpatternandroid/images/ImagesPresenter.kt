package com.example.archpatternandroid.images

import java.io.File

class ImagesPresenter(
    var photosView: ImagesContract.View)
    : ImagesContract.Presenter{

    var selectedFile : String? = null

    /**
     * ambil gambar dan cek permission
     * cek photos bukan null
     * */
    override fun takePhotos() {
        if (!photosView.checkSelfPermission()) {
            photosView.onShowPermissionDialog(false)
            return
        }

        val file = photosView.newFile()

        if (file == null) {
            photosView.onShowErrorDialog()
            return
        }
        photosView.takePhotosView(file)
    }

    /**
     * ambil gambar dari gallery
     * */
    override fun selectPhotos() {
        if (!photosView.checkSelfPermission()) {
            photosView.onShowPermissionDialog(true)
            return
        }
        photosView.selectPhotosView()
    }

    // preview photos to user
    override fun showPreview(file: File) {
        photosView.onShowPhotosPreview(file)
    }

    // save photos
    override fun savePhotos(filePath: String) {
        selectedFile = filePath
    }

    // get photos
    override fun getPhotos(): String {
        return selectedFile!!
    }
}