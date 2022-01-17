package com.example.courseselectiondemo.logic.dao

import android.util.Log
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.SaveListener
import com.example.courseselectiondemo.Course
import com.example.courseselectiondemo.CourseHelper
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait
import java.util.concurrent.locks.Lock

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

    /*
    override fun findObjectIdByName(name: String): String {
        CourseHelper.objectId = ""
        val query = BmobQuery<Course>()
        query.addWhereEqualTo("name", name)
        query.findObjects(object : FindListener<Course>() {
            override fun done(list: List<Course>, e: BmobException?) {
                if (e == null) {
                    if (list.isNotEmpty()) {
                        CourseHelper.objectId = list[0].objectId
                    }
                    else {
                        Log.d("CourseImpl:", "不存在该课程名")
                    }
                }
                else {
                    Log.e("CourseImpl:", ""+ e.message)
                }
            }
        })
    }
     */

    override suspend fun writeObjectIdByName(name: String): Unit = runBlocking{
        coroutineScope {
            val query = BmobQuery<Course>()
            query.addWhereEqualTo("name", name)
            query.findObjects(object : FindListener<Course>() {
                override fun done(list: List<Course>, e: BmobException?) {
                    if (e == null) {
                        if (list.isNotEmpty()) {
                            CourseHelper.objectId = list[0].objectId
                        }
                        else {
                            Log.d("CourseImpl:", "不存在该课程名")
                        }
                    }
                    else {
                        Log.e("CourseImpl:", ""+ e.message)
                    }
                }
            })
        }
    }

    /*
    override fun getObjectIdFromHelper(): String {
        return CourseHelper.objectId
    }
    */
}