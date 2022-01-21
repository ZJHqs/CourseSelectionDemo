package com.example.courseselectiondemo.ui.teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.courseselectiondemo.R
import com.example.courseselectiondemo.databinding.ActivityTeacherMainBinding

class TeacherMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeacherMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_teacher_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_teacher_main)
        binding.showTeachingCourse.setOnClickListener {
            val intent = Intent(this, ShowTeachingCourse::class.java)
            startActivity(intent)
        }
    }
}