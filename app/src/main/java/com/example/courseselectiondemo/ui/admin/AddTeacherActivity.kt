package com.example.courseselectiondemo.ui.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.SaveListener
import com.example.courseselectiondemo.CourseSelectionApplication
import com.example.courseselectiondemo.R
import com.example.courseselectiondemo.Student
import com.example.courseselectiondemo.Teacher
import com.example.courseselectiondemo.databinding.ActivityAddTeacherBinding

class AddTeacherActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddTeacherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_teacher)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_teacher)
        binding.addTeacher7.setOnClickListener {
            val query = BmobQuery<Teacher>()
            query.addWhereEqualTo("id", binding.teacherId7.text.toString())
            query.findObjects(object : FindListener<Teacher>() {
                override fun done(list : List<Teacher>, e : BmobException?) {
                    if (e == null) {
                        if (list.isNotEmpty()) {
                            Toast.makeText(CourseSelectionApplication.context, "教师号重复", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            val teacher = Teacher()
                            teacher.id = binding.teacherId7.text.toString()
                            teacher.name = binding.teacherName7.text.toString()
                            teacher.password = "123456"
                            teacher.save(object : SaveListener<String>() {
                                override fun done(objectId: String?, e1: BmobException?) {
                                    if (e1 == null) {
                                        Toast.makeText(CourseSelectionApplication.context, "新建成功", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            })
                        }
                    }
                }
            })
        }
        binding.cancel7.setOnClickListener {
            finish()
        }
    }
}