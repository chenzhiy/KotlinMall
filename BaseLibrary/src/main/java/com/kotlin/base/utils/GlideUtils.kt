package com.kotlin.base.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.kotlin.base.R

object GlideUtils {
    fun loadImage(context: Context,url:String,imageView: ImageView){
        Glide.with(context).load(url).centerCrop().into(imageView)
    }

    fun loadImageFitCenter(context: Context,url:String,imageView: ImageView){
        Glide.with(context).load(url).fitCenter().into(imageView)
    }

    //当天fragment或者activity 失去焦点或者distoryed的时候，Glide 会自动停止加载相关资源，确保资源不被浪费
    fun loadUrlImage(context: Context,url:String,imageView: ImageView){
        Glide.with(context).load(url).placeholder(R.drawable.icon_back).error(R.drawable.icon_back).centerCrop().into(
            object : SimpleTarget<GlideDrawable>(){
                override fun onResourceReady(resource: GlideDrawable,glideAnimation:GlideAnimation<in GlideDrawable>
                ){
                    imageView.setImageDrawable(resource)
                }
            }
        )
    }

}