package com.example.courseselectiondemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.courseselectiondemo.Course
import com.example.courseselectiondemo.CourseSelectionApplication
import com.example.courseselectiondemo.R

class ShowCourse : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_course)
    }

    override fun onResume() {
        super.onResume()
        val bmobQuery = BmobQuery<Course>()
        bmobQuery.findObjects(object : FindListener<Course>() {
            override fun done(list : List<Course>, e : BmobException?) {
                if (e == null) {
                    val layoutManager = LinearLayoutManager(this@ShowCourse)
                    val recyclerView : RecyclerView = findViewById(R.id.courseRecyclerView)
                    recyclerView.layoutManager = layoutManager
                    val adapter = CourseAdapter(list)
                    recyclerView.adapter = adapter
                    adapter.setOnItemCLickListener(object : CourseAdapter.OnItemClickListener {
                        override fun onClick(position: Int) {
                            Toast.makeText(CourseSelectionApplication.context, "您点击的是 $position 行！", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        })
    }
}