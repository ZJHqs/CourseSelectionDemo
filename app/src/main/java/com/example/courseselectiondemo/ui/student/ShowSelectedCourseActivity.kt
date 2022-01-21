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

class ShowSelectedCourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowCourse1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_show_selected_course)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_course1)

    }

    override fun onResume() {
        super.onResume()
        val bmobQuery = BmobQuery<CourseSelection>()
        bmobQuery.addWhereEqualTo("sid", User.id)
        bmobQuery.findObjects(object : FindListener<CourseSelection>() {
            override fun done(list : List<CourseSelection>, e : BmobException?) {
                if (e == null) {
                    if (list.isNotEmpty()) {
                        val set = HashSet<String>()
                        for (courseSelection in list) {
                            set.add(courseSelection.cid)
                        }
                        val list1 = ArrayList<Course1>()
                        val query1 = BmobQuery<Course1>()
                        query1.findObjects(object : FindListener<Course1>() {
                            override fun done(list2: List<Course1>, queryException : BmobException?) {
                                for (course in list2) {
                                    if (set.contains(course.cid)) {
                                        list1.add(course)
                                    }
                                }
                                val layoutManager = LinearLayoutManager(this@ShowSelectedCourseActivity)
                                val recyclerView : RecyclerView = findViewById(R.id.courseRecyclerView1)
                                recyclerView.layoutManager = layoutManager
                                val adapter = CourseAdapter1(list1)
                                recyclerView.adapter = adapter
                                adapter.setOnItemCLickListener(object : CourseAdapter1.OnItemClickListener {
                                    override fun onClick(position: Int) {
                                        CourseHelper1.objectId = list1[position].objectId
                                        CourseHelper1.cid = list1[position].cid
                                        CourseHelper1.cname = list1[position].name
                                        CourseHelper1.tid = list1[position].tid
                                        CourseHelper1.address = list1[position].address
                                        CourseHelper1.selected_num = list1[position].selected_num
                                        CourseHelper1.max_num = list1[position].max_num
                                        val query2 = BmobQuery<Teacher>()
                                        query2.addWhereEqualTo("id", list1[position].tid)
                                        query2.findObjects(object : FindListener<Teacher>() {
                                            override fun done(list3 : List<Teacher>, e : BmobException?) {
                                                if (e == null) {
                                                    CourseHelper1.tname = list3[0].name.toString()
                                                    CourseHelper1.phone = list3[0].phone
                                                }
                                            }
                                        })
                                        val intent = Intent(this@ShowSelectedCourseActivity, DetailCourseActivity::class.java)
                                        startActivity(intent)
                                        //这里是为了让程序不出现错误
                                        //TODO
//                                        finish()
                                    }
                                })
                            }
                        })
                    }
                    else {
                        val courseList = ArrayList<Course1>()
                        val layoutManager = LinearLayoutManager(this@ShowSelectedCourseActivity)
                        val recyclerView : RecyclerView = findViewById(R.id.courseRecyclerView1)
                        recyclerView.layoutManager = layoutManager
                        val adapter = CourseAdapter1(courseList)
                        recyclerView.adapter = adapter
                        Toast.makeText(CourseSelectionApplication.context, "您还未选课！", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}