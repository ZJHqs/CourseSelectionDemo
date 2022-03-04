package com.example.courseselectiondemo.ui.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.UpdateListener
import com.example.courseselectiondemo.*
import com.example.courseselectiondemo.databinding.ActivityDeleteTeacherBinding

class DeleteTeacherActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDeleteTeacherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_delete_teacher)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_delete_teacher)
        binding.deleteTeacher8.setOnClickListener {
            val query = BmobQuery<Teacher>()
            query.addWhereEqualTo("id", binding.teacherId8.text.toString())
            query.findObjects(object : FindListener<Teacher>() {
                override fun done(list : List<Teacher>, e : BmobException?) {
                    if (e == null) {
                        if (list.isEmpty()) {
                            Toast.makeText(CourseSelectionApplication.context, "要删除的教师不存在", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            val query1 = BmobQuery<Course1>()
                            query1.addWhereEqualTo("tid", binding.teacherId8.text.toString())
                            query1.findObjects(object : FindListener<Course1>() {
                                override fun done(list1: List<Course1>, e1 : BmobException?) {
                                    if (e1 == null) {
                                        if (list1.isEmpty()) {
                                            val teacher = Teacher()
                                            teacher.objectId = list[0].objectId
                                            teacher.delete(object : UpdateListener() {
                                                override fun done(e2: BmobException?) {
                                                    if (e2 == null) {
                                                        Toast.makeText(CourseSelectionApplication.context, "删除教师成功!", Toast.LENGTH_SHORT)
                                                            .show()
                                                    }
                                                }
                                            })
                                        }
                                        else {
                                            Toast.makeText(CourseSelectionApplication.context, "该教师有课程，无法删除！", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            })
                        }
                    }
                }
            })
        }
        binding.cancel8.setOnClickListener {
            finish()
        }
    }
}