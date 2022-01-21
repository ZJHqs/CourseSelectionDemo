package com.example.courseselectiondemo.ui.teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.courseselectiondemo.Course1
import com.example.courseselectiondemo.CourseHelper1
import com.example.courseselectiondemo.R
import com.example.courseselectiondemo.User
import com.example.courseselectiondemo.databinding.ActivityShowTeachingCourseBinding

class ShowTeachingCourse : AppCompatActivity() {

    private lateinit var binding: ActivityShowTeachingCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_show_teaching_course)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_teaching_course)
    }

    override fun onResume() {
        super.onResume()
        val query = BmobQuery<Course1>()
        query.addWhereEqualTo("tid", User.id)
        query.findObjects(object : FindListener<Course1>() {
            override fun done(list: List<Course1>, e : BmobException?) {
                if (e == null) {
                    val layoutManager = LinearLayoutManager(this@ShowTeachingCourse)
                    val recyclerView : RecyclerView = findViewById(R.id.courseRecyclerView2)
                    recyclerView.layoutManager = layoutManager
                    val adapter = CourseAdapter2(list)
                    recyclerView.adapter = adapter
                    adapter.setOnItemCLickListener(object : CourseAdapter2.OnItemClickListener {
                        override fun onClick(position: Int) {
                            //此处将cid保存起来，以便后续通过cid查找sid
                            CourseHelper1.cid = list[position].cid
                            val intent = Intent(this@ShowTeachingCourse, ShowTeachingStudent::class.java)
                            startActivity(intent)
                        }
                    })
                }
            }
        })
    }
}