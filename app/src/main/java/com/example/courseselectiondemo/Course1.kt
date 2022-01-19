package com.example.courseselectiondemo

import cn.bmob.v3.BmobObject

class Course1 : BmobObject() {
    var cid : String = ""
    var name : String = ""
    var tid : String = ""
    var selected_num : Int? = null
    var max_num : Int? = null
}