package com.example.courseselectiondemo.ui.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.UpdateListener
import com.example.courseselectiondemo.CourseSelection
import com.example.courseselectiondemo.CourseSelectionApplication
import com.example.courseselectiondemo.R
import com.example.courseselectiondemo.Student
import com.example.courseselectiondemo.databinding.ActivityDeleteStudentBinding

class DeleteStudentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDeleteStudentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_delete_student)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_delete_student)
        binding.deleteStudent6.setOnClickListener {
            /**
             * 先判断学生存不存在
             */
            val query = BmobQuery<Student>()
            query.addWhereEqualTo("id", binding.studentId6.text.toString())
            query.findObjects(object : FindListener<Student>() {
                override fun done(list: List<Student>, e : BmobException?) {
                    if (e == null) {
                        if (list.isEmpty()) {
                            Toast.makeText(CourseSelectionApplication.context, "要删除的学生不存在！", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            val query1 = BmobQuery<CourseSelection>()
                            query1.addWhereEqualTo("sid", binding.studentId6.text.toString())
                            query1.findObjects(object : FindListener<CourseSelection>() {
                                override fun done(list1: List<CourseSelection>, e1 : BmobException?) {
                                    if (e1 == null) {
                                        /**
                                         * 该学生没选课才可以删除
                                         */
                                        if (list1.isEmpty()) {
                                            val student = Student()
//                                            student.id = binding.studentId6.text.toString()
                                            student.objectId = list[0].objectId
                                            student.delete(object : UpdateListener() {
                                                override fun done(e2: BmobException?) {
                                                    if (e2 == null) {
                                                        Toast.makeText(CourseSelectionApplication.context, "删除学生成功", Toast.LENGTH_SHORT).show()
                                                    }
                                                }
                                            })
                                        }
                                        else {
                                            Toast.makeText(CourseSelectionApplication.context, "该学生有选课，无法删除", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            })
                        }
                    }
                }
            })
        }
        binding.cancel6.setOnClickListener {
            finish()
        }
    }
}