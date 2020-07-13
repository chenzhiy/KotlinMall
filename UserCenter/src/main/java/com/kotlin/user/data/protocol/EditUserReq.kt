package com.kotlin.user.data.protocol

//修改用户资料
data class EditUserReq (val userIcon:String,val userName:String,val userGender:String,val userSign:String)