package com.example.courseselectiondemo.ui.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.courseselectiondemo.Course1
import com.example.courseselectiondemo.R
import com.example.courseselectiondemo.databinding.ActivityShowCourse1Binding
import com.example.courseselectiondemo.ui.CourseAdapter1

class ShowOptionalCourseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowCourse1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_show_selected_course)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_course1)
        val bmobQuery = BmobQuery<Course1>()
        bmobQuery.findObjects(object : FindListener<Course1>() {
            override fun done(list: List<Course1>, e: BmobException?) {
                if (e == null) {
                    val list1 = ArrayList<Course1>()
                    for (course in list) {
                        if (course.selected_num < course.max_num) {
                            list1.add(course)
                        }
                    }
                    val layoutManager = LinearLayoutManager(this@ShowOptionalCourseActivity)
                    val recyclerView: RecyclerView = findViewById(R.id.courseRecyclerView1)
                    recyclerView.layoutManager = layoutManager
                    val adapter = CourseAdapter1(list1)
                    recyclerView.adapter = adapter
                }
            }
        })
    }
}