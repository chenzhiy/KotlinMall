package com.kotlin.base.data.protocol

class BaseResp<out T>(val status:Int,val msg:String, val data:T) {
}