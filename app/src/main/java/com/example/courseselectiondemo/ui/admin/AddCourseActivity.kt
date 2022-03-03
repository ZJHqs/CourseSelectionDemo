package com.example.courseselectiondemo.ui.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.SaveListener
import com.example.courseselectiondemo.*
import com.example.courseselectiondemo.databinding.ActivityAddCourseBinding

class AddCourseActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddCourseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_course)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_course)
        binding.addCourse3.setOnClickListener {
            /**
             * 先找课程id是否合法
             */
            val bmobQuery = BmobQuery<Course1>()
            bmobQuery.addWhereEqualTo("cid", binding.courseId3.text.toString())
            bmobQuery.findObjects(object : FindListener<Course1>() {
                override fun done(list : List<Course1>, e : BmobException?) {
                    if (e == null) {
                        if (list.isNotEmpty()) {
                            Toast.makeText(CourseSelectionApplication.context, "课程id重复", Toast.LENGTH_SHORT).show()
                        }
                        /**
                         * 查询该教师是否存在
                         */
                        else {
                            val query1 = BmobQuery<Teacher>()
                            query1.addWhereEqualTo("id", binding.teacherId3.text.toString())
                            query1.findObjects(object : FindListener<Teacher>() {
                                override fun done(list: List<Teacher>, e1 : BmobException?) {
                                    if (e1 == null) {
                                        if (list.isEmpty()) {
                                            Toast.makeText(CourseSelectionApplication.context, "该教师不存在", Toast.LENGTH_SHORT).show()
                                        }
                                        /**
                                         * 新增数据
                                         */
                                        else {
                                            val course = Course1()
                                            course.cid = binding.courseId3.text.toString()
                                            course.name = binding.courseName3.text.toString()
                                            course.tid = binding.teacherId3.text.toString()
                                            course.address = binding.address3.text.toString()
                                            course.max_num = binding.maxNum3.text.toString().toInt()
                                            course.selected_num = 0
                                            course.save(object : SaveListener<String>() {
                                                override fun done(objectId: String?, e2: BmobException?) {
                                                    if (e2 == null) {
                                                        Toast.makeText(CourseSelectionApplication.context, "添加成功", Toast.LENGTH_SHORT)
                                                            .show()
                                                    }
                                                    else {
                                                        Toast.makeText(CourseSelectionApplication.context, "添加失败", Toast.LENGTH_SHORT)
                                                            .show()
                                                    }
                                                }
                                            })
                                        }
                                    }
                                }
                            })
                        }
                    }
                }
            })
        }
        binding.cancel3.setOnClickListener {
            finish()
        }
    }
}