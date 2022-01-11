package com.example.courseselectiondemo.logic.dao

import android.util.Log
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.QueryListListener
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.SaveListener
import com.example.courseselectiondemo.CourseSelectionApplication
import com.example.courseselectiondemo.Student


class StudentImpl : StudentInterface {
    override fun query(id: String, password: String) {
        val bmobQuery1: BmobQuery<Student> = BmobQuery()
        val bmobQuery2: BmobQuery<Student> = BmobQuery()
        bmobQuery1.addWhereEqualTo("objectId", id)
        bmobQuery2.addWhereEqualTo("password", password)
        val queries = ArrayList<BmobQuery<Student>>()
        queries.add(bmobQuery1)
        queries.add(bmobQuery2)
        val bmobQuery = BmobQuery<Student>()
        bmobQuery.and(queries)
        bmobQuery.findObjects(object : FindListener<Student>() {
            override fun done(list: List<Student>, e: BmobException?){
                if (e == null) {
                    if (list.isNotEmpty()) {
                        Toast.makeText(CourseSelectionApplication.context, "有该用户", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(CourseSelectionApplication.context, "该用户不存在！", Toast.LENGTH_SHORT).show()
                    }
//                    Log.d("QUERY", ""+list.size)
                }
                else {
                    Log.e("BMOB", e.toString())
                }
            }
        })
    }

    override fun add(name: String, password: String) {
        val student : Student = Student()
        student.name = name
        student.password = password
        student.save(object : SaveListener<String>() {
            override fun done(objectId: String?, e: BmobException?) {
                if (e == null) {

                }
                else {
                    Log.e("CREATE","Failed!" + e.message)
                }
            }
        })
    }

    override fun delete(name: String) {

    }
}