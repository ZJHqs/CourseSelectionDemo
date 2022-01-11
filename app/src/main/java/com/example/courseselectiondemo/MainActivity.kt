package com.example.courseselectiondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobObject
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
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
//                val id = binding.uid.text.toString()
//                val password = binding.upassword.text.toString()
//                Log.d("QUERY","name:$id")
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
        以下是为了初始化数据库
         */
        binding.button.setOnClickListener {
            val student = StudentImpl()
            student.add("钟俊豪", "123456")
        }
    }

}
