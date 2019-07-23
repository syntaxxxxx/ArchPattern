package com.example.archpatternandroid.register

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.archpatternandroid.images.ImagesContract
import id.zelory.compressor.Compressor
import kotlinx.android.synthetic.main.activity_register_actiivty.*
import java.io.File
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import android.provider.MediaStore
import androidx.core.content.FileProvider
import android.content.Intent
import android.util.Log
import com.example.archpatternandroid.BuildConfig
import org.jetbrains.anko.toast
import android.R
import android.os.Environment
import java.io.IOException
import java.util.*


class RegisterActiivty : AppCompatActivity(), ImagesContract.View, RegisterContract.View, View.OnClickListener {

    lateinit var presenter: RegisterPresenter
    lateinit var compressor: Compressor
    var filePicture: File? = null

    val allPermission = arrayOf<String>(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    companion object {
        const val REQUEST_CAMERA = 0
        const val REQUEST_GALLERY: Int = 1
    }

    var name: String = ""
    var email: String = ""
    var password: String = ""
    var level: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.archpatternandroid.R.layout.activity_register_actiivty)

        initPresenter()
        initCompressImages()
        initRb()
        iv_profile.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            com.example.archpatternandroid.R.id.iv_profile -> {
            }
            com.example.archpatternandroid.R.id.rb_admin -> {
                level = "Admin"
            }
            com.example.archpatternandroid.R.id.rb_user -> {
                level = "User"
            }
            com.example.archpatternandroid.R.id.btn_register -> {
            }
            com.example.archpatternandroid.R.id.tv_login -> {
            }
        }
    }

    private fun initPresenter() {
        presenter = RegisterPresenter()
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
        val items = arrayOf<CharSequence>(
            getString(R.string.take_images),
            getString(R.string.choose_gallery),
            getString(R.string.cancel)
        )
        val builder = AlertDialog.Builder(this@SignUp)
        builder.setItems(items, { dialog, item ->
            if (items[item] == "Take Images") {
                imagesPresenter.takePicture()
            } else if (items[item] == "Choose from Library") {
                imagesPresenter.selectPicture()
            } else if (items[item] == "Cancel") {
                dialog.dismiss()
            }
        })
        builder.show()
    }

    override fun onShowErrorDialog() {
    }

    override fun getRealPathFromUri(uri: Uri): String {
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
