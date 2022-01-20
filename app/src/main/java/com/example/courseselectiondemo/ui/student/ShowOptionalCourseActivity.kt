package com.example.courseselectiondemo.ui.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.courseselectiondemo.*
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
                    adapter.setOnItemCLickListener(object : CourseAdapter1.OnItemClickListener {
                        override fun onClick(position: Int) {
//                            Toast.makeText(CourseSelectionApplication.context, "您点击的是 $position 行！", Toast.LENGTH_SHORT).show()
                            CourseHelper1.cid = list[position].cid
                            CourseHelper1.cname = list[position].name
                            CourseHelper1.tid = list[position].tid
                            CourseHelper1.address = list[position].address
                            CourseHelper1.selected_num = list[position].selected_num
                            CourseHelper1.max_num = list[position].max_num
                            val query1 = BmobQuery<Teacher>()
                            query1.addWhereEqualTo("id", list[position].tid)
                            query1.findObjects(object : FindListener<Teacher>() {
                                override fun done(list1 : List<Teacher>, e : BmobException?) {
                                    if (e == null) {
                                        CourseHelper1.tname = list1[0].name.toString()
                                        CourseHelper1.phone = list1[0].phone
                                    }
                                }
                            })
                            val intent = Intent(this@ShowOptionalCourseActivity, DetailCourseActivity::class.java)
                            startActivity(intent)
                        }
                    })
                }
            }
        })
    }
}