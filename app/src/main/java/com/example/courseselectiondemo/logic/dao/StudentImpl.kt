package com.example.courseselectiondemo.logic.dao

import android.util.Log
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.*
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
                }
                else {
                    Log.e("BMOB", e.toString())
                }
            }
        })
    }

    override fun add(name: String, password: String) {
        val student = Student()
        student.name = name
        student.password = password
        student.save(object : SaveListener<String>() {
            override fun done(objectId: String?, e: BmobException?) {
                if (e != null) {
                    Log.e("CREATE","Failed!" + e.message)
                }
            }
        })
    }

    override fun add(id: String, name: String, password: String) {
        val bmobQuery = BmobQuery<Student>()
        bmobQuery.addWhereEqualTo("id", id)
        bmobQuery.findObjects(object : FindListener<Student>() {
            override fun done(list :List<Student>, e : BmobException?) {
                if (e == null) {
                    if (list.isEmpty()) {
                        val student = Student()
                        student.id = id
                        student.name = name
                        student.password = password
                        student.save(object : SaveListener<String>() {
                            override fun done(objectId: String?, e: BmobException?) {
                                if (e != null) {
                                    Log.e("CREATE","Failed!" + e.message)
                                }
                            }
                        })
                    }
                    else {
                        Toast.makeText(CourseSelectionApplication.context, "id不能重复！", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

    }

    override fun delete(id: String) {
        val student = Student()
        student.objectId = id
        student.delete(object : UpdateListener() {
            override fun done(e: BmobException?) {
                if (e == null) {
                    Toast.makeText(CourseSelectionApplication.context, "删除成功！", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(CourseSelectionApplication.context, "该用户不存在", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun deleteById(id: String) {
        val bmobQuery = BmobQuery<Student>()
        bmobQuery.addWhereEqualTo("id", id)
        bmobQuery.findObjects(object : FindListener<Student>() {
            override fun done(list :List<Student>, e : BmobException?) {
                if (e == null) {
                    if (list.isNotEmpty()) {
                        val student = list[0]
                        student.delete(object : UpdateListener() {
                            override fun done(e: BmobException?) {
                                if (e == null) {
                                    Toast.makeText(CourseSelectionApplication.context, "删除成功！", Toast.LENGTH_SHORT).show()
                                }
                                else {
                                    Toast.makeText(CourseSelectionApplication.context, "该用户不存在", Toast.LENGTH_SHORT).show()
                                }
                            }
                        })
                    }
                    else {
                        Toast.makeText(CourseSelectionApplication.context, "用户不存在！", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}