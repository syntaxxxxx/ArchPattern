package com.example.archpatternandroid.register

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.example.archpatternandroid.BuildConfig
import com.example.archpatternandroid.R
import com.example.archpatternandroid.images.ImagesContract
import com.example.archpatternandroid.images.ImagesPresenter
import id.zelory.compressor.Compressor
import kotlinx.android.synthetic.main.activity_register_actiivty.*
import org.jetbrains.anko.toast
import java.io.File
import java.io.IOException
import java.util.*


class RegisterActiivty : AppCompatActivity(), ImagesContract.View, RegisterContract.View, View.OnClickListener {

    lateinit var imagesPresenter: ImagesPresenter
    lateinit var presenter: RegisterPresenter
    lateinit var compressor: Compressor

    var filePicture: File? = null

    var name: String = ""
    var email: String = ""
    var password: String = ""
    var level: String = ""

    val allPermission = arrayOf<String>(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    companion object {
        const val REQUEST_CAMERA = 0
        const val REQUEST_GALLERY: Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_actiivty)

        initPresenter()
        initCompressImages()
        initRb()
        iv_profile.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_profile -> {
            }
            R.id.rb_admin -> {
                level = "Admin"
            }
            R.id.rb_user -> {
                level = "User"
            }
            R.id.btn_register -> {
            }
            R.id.tv_login -> {
            }
        }
    }

    private fun initPresenter() {
        presenter = RegisterPresenter()
        imagesPresenter = ImagesPresenter(this)
    }

    private fun initCompressImages() {
        compressor = Compressor(this)
    }

    private fun initRb() {
        level = if (rb_admin.isChecked) {
            "Admin"
        } else {
            "User"
        }
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettach()
    }

    override fun onStart() {
        super.onStart()
        onAttachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDettachView()
    }

    override fun checkSelfPermission(): Boolean {
        for (permission in allPermission) {
            val result = ActivityCompat.checkSelfPermission(this, permission)
            if (result == PackageManager.PERMISSION_DENIED) return false
        }
        return true
    }

    override fun onShowPermissionDialog(boolean: Boolean) {
    }

    override fun takePhotosView(file: File?) {
        val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePicture.resolveActivity(packageManager) != null) {

            if (file != null) {
                val mPhotoURI = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file)
                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoURI)
                filePicture = file
                startActivityForResult(takePicture, REQUEST_CAMERA)
            } else {
                Log.d("TAG", "File Null")
            }

        } else {
            toast("can't take photos")
        }
    }

    override fun selectPhotosView() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivityForResult(intent, REQUEST_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onShowPhotosPreview(file: File) {
    }

    override fun onShowDialogSelectedPhotos() {
        val items = arrayOf("Take Photos", "Choose from Library", "Cancel")
        val builder = AlertDialog.Builder(this)
        builder.setItems(items, { dialog, item ->
            if (items[item] == "Take Photos") {
                imagesPresenter.takePhotos()
            } else if (items[item] == "Choose from Library") {
                imagesPresenter.selectPhotos()
            } else if (items[item] == "Cancel") {
                dialog.dismiss()
            }
        })
        builder.show()
    }

    override fun getRealPathFromUri(uri: Uri): String {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = contentResolver.query(uri, proj, null, null, null)
            assert(cursor != null)
            val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(columnIndex)
        } finally {
            if (cursor != null) {
                cursor.close()
            } else {
                Log.d("TAG", "Cursor Null")
            }
        }
    }

    override fun newFile(): File? {
        val cal = Calendar.getInstance()
        val timeInMillis = cal.getTimeInMillis()
        val mFileName = timeInMillis.toString() + ".jpeg"
        val filePath = getFilePath()
        try {
            val newFile = File(filePath?.getAbsolutePath(), mFileName)
            newFile.createNewFile()
            return newFile

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    override fun getFilePath(): File? {
        return getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    }
}
