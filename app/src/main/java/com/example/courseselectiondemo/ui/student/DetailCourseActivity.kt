package com.example.courseselectiondemo.ui.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.courseselectiondemo.*
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
        val query1 = BmobQuery<CourseSelection>()
        query1.addWhereEqualTo("sid", User.id)
        val query2 = BmobQuery<CourseSelection>()
        query2.addWhereEqualTo("cid", CourseHelper1.cid)
        val queries = ArrayList<BmobQuery<CourseSelection>>()
        queries.add(query1)
        queries.add(query2)
        val query3 = BmobQuery<CourseSelection>()
        query3.and(queries)
        query3.findObjects(object : FindListener<CourseSelection>() {
            override fun done(list2: List<CourseSelection>, e : BmobException?) {
                if (e == null) {
                    if (list2.isNotEmpty()) {
                        binding.selectOrGiveUp.text = "退选"
                    }
                    else {
                        binding.selectOrGiveUp.text = "选课"
                    }
                }
            }
        })
        binding.selectOrGiveUp.setOnClickListener {
            if (binding.selectOrGiveUp.text == "退选") {

            }
            else if (binding.selectOrGiveUp.text == "选课") {

            }
            else {
                Log.e("SelectOrGiveUp", "Button.text未赋值")
            }
        }
        binding.returnToMain.setOnClickListener {
            finish()
        }
    }
}