package com.example.courseselectiondemo.ui.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.courseselectiondemo.R
import com.example.courseselectiondemo.databinding.ActivityStudentMainBinding
import com.example.courseselectiondemo.ui.ShowCourse
import com.example.courseselectiondemo.ui.ShowCourse1

class StudentMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_student_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_student_main)
        binding.showAllCourse.setOnClickListener {
            val intent = Intent(this, ShowCourse1::class.java)
            startActivity(intent)
        }
        binding.showSelectedCourse.setOnClickListener {
            val intent = Intent(this, ShowSelectedCourseActivity::class.java)
            startActivity(intent)
        }
    }
}