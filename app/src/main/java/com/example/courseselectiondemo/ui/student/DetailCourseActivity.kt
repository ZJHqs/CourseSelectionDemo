package com.example.courseselectiondemo.ui.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.courseselectiondemo.CourseHelper1
import com.example.courseselectiondemo.R
import com.example.courseselectiondemo.Teacher

class DetailCourseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)
        val cname : TextView = findViewById(R.id.detail_course_cname)
        val tname : TextView = findViewById(R.id.detail_course_tname)
        val selected_num : TextView = findViewById(R.id.detail_course_selected_num)
        val max_num : TextView = findViewById(R.id.detail_course_max_num)
        val phone : TextView = findViewById(R.id.detail_course_tphone)
        val address : TextView = findViewById(R.id.detail_course_address)
        cname.text = CourseHelper1.cname
//        tname.text = CourseHelper1.tname
        selected_num.text = CourseHelper1.selected_num.toString()
        max_num.text = CourseHelper1.max_num.toString()
//        phone.text = CourseHelper1.phone
        address.text = CourseHelper1.address
        val query = BmobQuery<Teacher>()
        query.addWhereEqualTo("id", CourseHelper1.tid)
        query.findObjects(object : FindListener<Teacher>() {
            override fun done(list1 : List<Teacher>, e : BmobException?) {
                if (e == null) {
                    tname.text = list1[0].name
                    phone.text = list1[0].phone
                }
            }
        })
    }
}