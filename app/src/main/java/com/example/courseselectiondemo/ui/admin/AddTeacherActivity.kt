package com.example.courseselectiondemo.ui.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.courseselectiondemo.R
import com.example.courseselectiondemo.databinding.ActivityAddTeacherBinding

class AddTeacherActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddTeacherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_teacher)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_teacher)
    }
}