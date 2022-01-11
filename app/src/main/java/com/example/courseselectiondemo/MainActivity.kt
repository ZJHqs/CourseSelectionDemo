package com.example.courseselectiondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import cn.bmob.v3.Bmob
import com.example.courseselectiondemo.databinding.ActivityMainBinding
import com.example.courseselectiondemo.logic.dao.StudentImpl

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Bmob.initialize(CourseSelectionApplication.context, "b376a5290dd966c9d1444e85117e42d4")
        binding.login.setOnClickListener {
            if (binding.student.isChecked) {
                val student = StudentImpl()
                student.query(binding.uid.text.toString(), binding.upassword.text.toString())
            }
            else if (binding.teacher.isChecked) {
                Toast.makeText(CourseSelectionApplication.context, "您选择的是教师功能，暂未实现", Toast.LENGTH_SHORT).show()
            }
            else if (binding.admin.isChecked) {
                Toast.makeText(CourseSelectionApplication.context, "您选择的是管理员功能，暂未实现", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(CourseSelectionApplication.context, "Error!", Toast.LENGTH_SHORT).show()
            }
        }

        /*
        以下是为了测试功能
         */
//        binding.button.setOnClickListener {
//            val student = StudentImpl()
//            student.add("钟俊豪", "123456")
//        }
        binding.button.setOnClickListener {
            val student = StudentImpl()
            student.deleteById("3118000020")
        }
    }

}
