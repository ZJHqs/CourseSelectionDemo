package com.example.courseselectiondemo

import cn.bmob.v3.BmobObject

class Course : BmobObject() {
    var name: String? = null
    var type: String? = null
    var credit: Double = 0.0
}