package com.example.courseselectiondemo.ui.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.SaveListener
import com.example.courseselectiondemo.Course1
import com.example.courseselectiondemo.CourseSelectionApplication
import com.example.courseselectiondemo.R
import com.example.courseselectiondemo.Student
import com.example.courseselectiondemo.databinding.ActivityAddStudentBinding

class AddStudentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddStudentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_student)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_student)
        binding.addStudent5.setOnClickListener {
            val query = BmobQuery<Student>()
            query.addWhereEqualTo("id", binding.studentId5.text.toString())
            query.findObjects(object : FindListener<Student>() {
                override fun done(list : List<Student>, e : BmobException?) {
                    if (e == null) {
                        if (list.isEmpty()) {
                            val student = Student()
                            student.id = binding.studentId5.text.toString()
                            student.name = binding.studentName5.text.toString()
                            student.password = "123456"
                            student.save(object : SaveListener<String>() {
                                override fun done(objectId : String?, e2 : BmobException?) {
                                    if (e2 == null) {
                                        Toast.makeText(CourseSelectionApplication.context, "新建成功！", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            })
                        }
                        else {
                            Toast.makeText(CourseSelectionApplication.context, "学号重复！", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
        binding.cancel5.setOnClickListener {
            finish()
        }
    }
}