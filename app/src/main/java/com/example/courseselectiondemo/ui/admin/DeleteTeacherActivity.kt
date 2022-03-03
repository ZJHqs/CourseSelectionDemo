package com.example.courseselectiondemo.ui.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.courseselectiondemo.R
import com.example.courseselectiondemo.databinding.ActivityDeleteTeacherBinding

class DeleteTeacherActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDeleteTeacherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_delete_teacher)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_delete_teacher)
    }
}