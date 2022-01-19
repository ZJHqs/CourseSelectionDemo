package com.example.courseselectiondemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.courseselectiondemo.Course1
import com.example.courseselectiondemo.CourseSelectionApplication
import com.example.courseselectiondemo.R
import com.example.courseselectiondemo.databinding.ActivityShowCourse1Binding

class ShowCourse1 : AppCompatActivity() {

    private lateinit var binding: ActivityShowCourse1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_show_course1)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_course1)
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
//                            Toast.makeText(CourseSelectionApplication.context, "您点击的是 $position 行！", Toast.LENGTH_SHORT).show()

                        }
                    })
                }
            }
        })
    }
}