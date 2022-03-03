package com.example.courseselectiondemo.ui.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
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
    }
}