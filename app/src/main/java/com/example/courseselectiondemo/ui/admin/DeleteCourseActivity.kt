package com.example.courseselectiondemo.ui.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.UpdateListener
import com.example.courseselectiondemo.Course1
import com.example.courseselectiondemo.CourseSelectionApplication
import com.example.courseselectiondemo.R
import com.example.courseselectiondemo.databinding.ActivityDeleteCourseBinding

class DeleteCourseActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDeleteCourseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_delete_course)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_delete_course)
        binding.deleteCourse4.setOnClickListener {
            val query = BmobQuery<Course1>()
            query.addWhereEqualTo("cid", binding.courseId4.text.toString())
            query.findObjects(object : FindListener<Course1>(){
                override fun done(list : List<Course1>, e : BmobException?) {
                    if (e == null) {
                        if (list.isNotEmpty()) {
                            if (list[0].selected_num > 0) {
                                Toast.makeText(CourseSelectionApplication.context, "仍有数据，不允许删除", Toast.LENGTH_SHORT).show()
                            }
                            else {
                                val course = Course1()
                                course.objectId = list[0].objectId
                                course.delete(object : UpdateListener() {
                                    override fun done(e1: BmobException?) {
                                        if (e1 == null) {
                                            Toast.makeText(CourseSelectionApplication.context, "删除课程成功", Toast.LENGTH_SHORT).show()
                                        }
                                        else {
                                            // TODO
                                        }
                                    }
                                })
                            }
                        }
                        else {
                            Toast.makeText(CourseSelectionApplication.context, "要删除的课程不存在", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
        binding.cancel4.setOnClickListener {
            finish()
        }
    }
}