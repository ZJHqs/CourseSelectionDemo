package com.example.courseselectiondemo.logic.dao

import android.util.Log
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.example.courseselectiondemo.Course

class CourseImpl : CourseInterface {
    override fun add(name: String, type: String, credit: Double) {
        val course = Course()
        course.name = name
        course.type = type
        course.credit = credit
        course.save(object : SaveListener<String>() {
            override fun done(objectId: String?, e: BmobException?) {
                if (e != null) {
                    Log.e("CREATE", "Failed!" + e.message)
                }
            }
        })
    }

}