package com.kotlin.base.widgets

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView
import com.kotlin.base.utils.GlideUtils
import org.jetbrains.anko.dimen

//圆角图标，左上和右上圆角

class RoundRectImageView @JvmOverloads constructor(context: Context,attrs:AttributeSet? = null,defStyleAttr:Int = 0):
    androidx.appcompat.widget.AppCompatImageView(context,attrs,defStyleAttr) {
    val radius = dimen(6).toFloat()
    private val radiusArray:FloatArray = floatArrayOf(radius,radius
    ,radius,radius,0.0f,0.0f,0.0f,0.0f)

    private fun drawRoundAngle(paramCanvas: Canvas){
        val paint = Paint()
        paint.isAntiAlias = true
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)

        val path = Path()
        path.addRoundRect(RectF(0.0f,0.0f,width.toFloat(),height.toFloat()),this.radiusArray,Path.Direction.CW)

        path.fillType = Path.FillType.INVERSE_WINDING
        paramCanvas.drawPath(path,paint)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        var bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888)
        var localCanvas = Canvas(bitmap)
        if (bitmap.isRecycled){
            bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888)
            localCanvas = Canvas(bitmap)
        }
    }

    fun loadUrl(imageUrl:String){
        GlideUtils.loadUrlImage(context,imageUrl,this)
    }
}