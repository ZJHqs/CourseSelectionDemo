package com.example.courseselectiondemo.ui.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.courseselectiondemo.CourseHelper1
import com.example.courseselectiondemo.R
import com.example.courseselectiondemo.Teacher
import com.example.courseselectiondemo.databinding.ActivityDetailCourseBinding

class DetailCourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_course)
        binding.detailCourseCname.text = CourseHelper1.cname
        binding.detailCourseSelectedNum.text = CourseHelper1.selected_num.toString()
        binding.detailCourseMaxNum.text = CourseHelper1.max_num.toString()
        binding.detailCourseAddress.text = CourseHelper1.address
        val query = BmobQuery<Teacher>()
        query.addWhereEqualTo("id", CourseHelper1.tid)
        query.findObjects(object : FindListener<Teacher>() {
            override fun done(list1 : List<Teacher>, e : BmobException?) {
                if (e == null) {
                    binding.detailCourseTname.text = list1[0].name
                    binding.detailCourseTphone.text = list1[0].phone
                }
            }
        })

    }
}