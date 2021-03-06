package com.example.courseselectiondemo.ui.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import cn.bmob.v3.BmobQuery
import com.example.courseselectiondemo.Course1
import com.example.courseselectiondemo.R
import com.example.courseselectiondemo.databinding.ActivityAdminMainBinding

class AdminMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_admin_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_main)
        binding.addCourse.setOnClickListener {
            val intent = Intent(this, AddCourseActivity::class.java)
            startActivity(intent)
        }
        /**
         * 目前的策略是有人选课的课程不给删除
         */
        binding.deleteCourse.setOnClickListener {
            val intent = Intent(this, DeleteCourseActivity::class.java)
            startActivity(intent)
        }
        binding.addStudent.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
        binding.deleteStudent.setOnClickListener {
            val intent = Intent(this, DeleteStudentActivity::class.java)
            startActivity(intent)
        }
        binding.addTeacher.setOnClickListener {
            val intent = Intent(this, AddTeacherActivity::class.java)
            startActivity(intent)
        }
        binding.deleteTeacher.setOnClickListener {
            val intent = Intent(this, DeleteTeacherActivity::class.java)
            startActivity(intent)
        }
    }
}