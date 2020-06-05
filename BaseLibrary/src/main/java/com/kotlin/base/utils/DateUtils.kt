package com.kotlin.base.utils

import android.provider.ContactsContract
import java.lang.String.format
import java.util.*

object DateUtils {

    fun getNow(format:String):String{
        return format.format(Date(),format)
    }

    fun dataToLong(date: Date):Long{
        return date.time
    }

    var TIMEZONE = "Asia/Shanghai"

    val defTimeZone:TimeZone
        get() = TimeZone.getTimeZone(TIMEZONE)

    val curTime:Long
    get() {
        val c = Calendar.getInstance(defTimeZone)
        return c.timeInMillis
    }
}