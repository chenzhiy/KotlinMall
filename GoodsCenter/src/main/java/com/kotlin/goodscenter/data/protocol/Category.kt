package com.kotlin.goodscenter.data.protocol


//分类实体类
data class Category(val id:Int, val parentId:Int, var isSelected:Boolean)