package com.maple.mynewdemo.dialog

import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ImageUtils
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.luck.picture.lib.tools.BitmapUtils
import com.maple.mynewdemo.R
import com.maple.mynewdemo.snackbar.AnimatedSnackbar
import com.maple.mynewdemo.utils.LogUtils
import com.maple.mynewdemo.widget.GlideEngine

class DialogActivity : AppCompatActivity() {
    private lateinit var ivImg: ImageView
    private lateinit var tvSize: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        this.ivImg = this.findViewById(R.id.iv_img)
        this.tvSize = this.findViewById(R.id.tv_size)

        // dialog 1
        this.findViewById<Button>(R.id.btn_dialog1).setOnClickListener {
            openCamer()
        }
        this.findViewById<Button>(R.id.btn_dialog2).setOnClickListener {
            openPhoto()
        }

    }

    private fun openCamer() {
        PictureSelector.create(this)
            .openCamera(PictureMimeType.ofImage())
            .imageEngine(GlideEngine.createGlideEngine())
            .isCompress(true)// 是否压缩
            .forResult(object : OnResultCallbackListener<LocalMedia> {
                override fun onResult(result: MutableList<LocalMedia>?) {
                    result?.last()?.let {
                        var compressPath:String? = ""
                        if(it.isCompressed && !TextUtils.isEmpty(it.compressPath))
                            compressPath = it.compressPath
                        else{
                            compressPath = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                                it.androidQToPath
                            }else{
                                it.path
                            }
                        }
                        //上传图片
                        if(!TextUtils.isEmpty(compressPath)){
                            //uploadImage(compressPath)
                           // showToast("type->${type}-图片大小：${FileUtils.getSize(compressPath)}")
                           // binding.ivPicture.loadImage(compressPath)
                           LogUtils.logGGQ("--------->${compressPath}")
                           val bitmap: Bitmap = ImageUtils.getBitmap(compressPath)
                           this@DialogActivity.ivImg.setImageBitmap(bitmap)
                           this@DialogActivity.tvSize.text = FileUtils.getSize(compressPath)
                        }else{
                            AnimatedSnackbar(this@DialogActivity)
                                .setMessage("拍照出错,请重新拍照！")
                                .show()
                        }
                    }?:let {
                        AnimatedSnackbar(this@DialogActivity)
                            .setMessage("拍照出错,请重新拍照！")
                            .show()
                    }
                }

                override fun onCancel() {
                    AnimatedSnackbar(this@DialogActivity)
                        .setMessage("拍照取消")
                        .show()
                }
            })
    }

    private fun openPhoto() {
        PictureSelector.create(this)
            .openGallery(PictureMimeType.ofImage())
            .imageEngine(GlideEngine.createGlideEngine())
            .selectionMode(PictureConfig.SINGLE)
            .isCamera(false)
            .isCompress(true)// 是否压缩
            .isGif(false)
            .forResult(object :OnResultCallbackListener<LocalMedia>{
                override fun onResult(result: MutableList<LocalMedia>?) {
                    result?.last()?.let {
                        var compressPath:String? = ""
                        if(it.isCompressed && !TextUtils.isEmpty(it.compressPath))
                            compressPath = it.compressPath
                        else{
                            compressPath = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                                it.androidQToPath
                            }else{
                                it.path
                            }
                        }
                        //上传图片
                        if(!TextUtils.isEmpty(compressPath)){
                            LogUtils.logGGQ("--------->${compressPath}")
                            val bitmap: Bitmap = ImageUtils.getBitmap(compressPath)
                            this@DialogActivity.ivImg.setImageBitmap(bitmap)
                            this@DialogActivity.tvSize.text = FileUtils.getSize(compressPath)
                        }else{
                            AnimatedSnackbar(this@DialogActivity)
                                .setMessage("选择照片出错,请重新选择！")
                                .show()
                        }
                    }?:let {
                        AnimatedSnackbar(this@DialogActivity)
                            .setMessage("选择照片出错,请重新选择！")
                            .show()
                    }
                }

                override fun onCancel() {
                    AnimatedSnackbar(this@DialogActivity)
                        .setMessage("选择照片取消")
                        .show()
                }
            })
    }
}