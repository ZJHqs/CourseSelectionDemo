package com.example.courseselectiondemo.ui

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
import com.example.courseselectiondemo.ui.student.DetailCourseActivity

class ShowCourse1 : AppCompatActivity() {

    private lateinit var binding: ActivityShowCourse1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_show_course1)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_course1)
    }

    override fun onResume() {
        super.onResume()
        val bmobQuery = BmobQuery<Course1>()
        bmobQuery.findObjects(object : FindListener<Course1>() {
            override fun done(list: List<Course1>, e : BmobException?) {
                if (e == null) {
                    val layoutManager = LinearLayoutManager(this@ShowCourse1)
                    val recyclerView : RecyclerView = findViewById(R.id.courseRecyclerView1)
                    recyclerView.layoutManager = layoutManager
                    val adapter = CourseAdapter1(list)
                    recyclerView.adapter = adapter
                    adapter.setOnItemCLickListener(object : CourseAdapter1.OnItemClickListener {
                        override fun onClick(position: Int) {
                            CourseHelper1.objectId = list[position].objectId
//                            Toast.makeText(CourseSelectionApplication.context, "您点击的是 $position 行！", Toast.LENGTH_SHORT).show()
                            CourseHelper1.cid = list[position].cid
                            CourseHelper1.cname = list[position].name
                            CourseHelper1.tid = list[position].tid
                            CourseHelper1.address = list[position].address
                            CourseHelper1.selected_num = list[position].selected_num
                            CourseHelper1.max_num = list[position].max_num
                            val query = BmobQuery<Teacher>()
                            query.addWhereEqualTo("id", list[position].tid)
                            query.findObjects(object : FindListener<Teacher>() {
                                override fun done(list1 : List<Teacher>, e : BmobException?) {
                                    if (e == null) {
                                        CourseHelper1.tname = list1[0].name.toString()
                                        CourseHelper1.phone = list1[0].phone
                                    }
                                }
                            })

                            val intent = Intent(this@ShowCourse1, DetailCourseActivity::class.java)
                            startActivity(intent)
                            //这里是为了让程序不出现错误
                            //TODO
//                            finish()
                        }
                    })
                }
            }
        })
    }
}