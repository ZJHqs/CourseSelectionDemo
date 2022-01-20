package com.example.courseselectiondemo.ui.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.SaveListener
import cn.bmob.v3.listener.UpdateListener
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
                        val query4 = BmobQuery<Course1>()
                        query4.addWhereEqualTo("cid", CourseHelper1.cid)
                        query4.findObjects(object : FindListener<Course1>() {
                            override fun done(list3: List<Course1>, e1 : BmobException?) {
                                if (e1 == null) {
                                    if (list3[0].selected_num == list3[0].max_num) {
                                        binding.selectOrGiveUp.text = "人数已满"
                                        binding.selectOrGiveUp.isEnabled = false
                                    }
                                    else {
                                        binding.selectOrGiveUp.text = "选课"
                                    }
                                }
                            }
                        })
                    }
                }
            }
        })
        binding.selectOrGiveUp.setOnClickListener {
            when (binding.selectOrGiveUp.text) {
                "退选" -> {

                    binding.selectOrGiveUp.text = "操作中..."
                    binding.selectOrGiveUp.isEnabled = false
                    /**
                     * 修改Course表中selected_num的值（减一）
                     */
                    val updateCourse = Course1()
                    updateCourse.cid = CourseHelper1.cid
                    updateCourse.name = CourseHelper1.cname
                    updateCourse.tid = CourseHelper1.tid
                    updateCourse.selected_num = CourseHelper1.selected_num - 1
                    updateCourse.max_num = CourseHelper1.max_num
                    updateCourse.address = CourseHelper1.address
                    updateCourse.update(CourseHelper1.objectId,object : UpdateListener() {
                        override fun done(updateException: BmobException?) {
                            if (updateException != null) {
                                Log.e("UpdateCourse", "" + updateException.message)
                            }
                            else {

                                binding.detailCourseSelectedNum.text = updateCourse.selected_num.toString()
                                CourseHelper1.selected_num--
                            }
                        }
                    })

                    /**
                     * 先将objectId读出来以便后续操作
                     */

                    val deleteQuery1 = BmobQuery<CourseSelection>()
                    val deleteQuery2 = BmobQuery<CourseSelection>()
                    deleteQuery1.addWhereEqualTo("sid", User.id)
                    deleteQuery2.addWhereEqualTo("cid", CourseHelper1.cid)
                    val deleteQueries = ArrayList<BmobQuery<CourseSelection>>()
                    deleteQueries.add(deleteQuery1)
                    deleteQueries.add(deleteQuery2)
                    val deleteQuery3 = BmobQuery<CourseSelection>()
                    deleteQuery3.and(deleteQueries)


                    /**
                     * 修改CourseSelection表
                     * 删除一项数据
                     */

                    deleteQuery3.findObjects(object : FindListener<CourseSelection>() {
                        override fun done(deleteList1 : List<CourseSelection>, e : BmobException?) {
                            if (e == null) {
                                //读出objectId
                                val objectId = deleteList1[0].objectId
                                val courseSelection = CourseSelection()
                                courseSelection.objectId = objectId
                                courseSelection.delete(object : UpdateListener() {
                                    override fun done(deleteException: BmobException?) {
                                        if (deleteException != null) {
                                            Log.e("DeleteCourseSelection", "" + deleteException.message)

                                        }
                                        else {
                                            binding.selectOrGiveUp.isEnabled = true
                                            binding.selectOrGiveUp.text = "选课"
                                        }
                                    }
                                })
                            }
                        }
                    })
                    Toast.makeText(CourseSelectionApplication.context, "退选成功", Toast.LENGTH_SHORT).show()

                }
                "选课" -> {
                    /**
                     * 因为能显示该选项的必然是符合条件的，所以不用考虑选课人数过多而导致的问题（非并发环境下）
                     * 为避免用户连续点击按钮而导致的不可预知的错误，先将button设置为不可点击
                     */
                    binding.selectOrGiveUp.text = "操作中..."
                    binding.selectOrGiveUp.isEnabled = false
                    /**
                     * 新增一个CourseSelection项
                     *
                     */
                    val newCourseSelection  = CourseSelection()
                    newCourseSelection.sid = User.id
                    newCourseSelection.cid = CourseHelper1.cid
                    newCourseSelection.save(object : SaveListener<String>() {
                        override fun done(objectId : String, newCourseSelectionException: BmobException?) {
                            if (newCourseSelectionException != null) {
                                Log.e("NewCourseSelection", ""+ newCourseSelectionException.message)
                            }
                        }
                    })
                    val updateCourse = Course1()
                    updateCourse.cid = CourseHelper1.cid
                    updateCourse.name = CourseHelper1.cname
                    updateCourse.tid = CourseHelper1.tid
                    updateCourse.selected_num = CourseHelper1.selected_num + 1
                    updateCourse.max_num = CourseHelper1.max_num
                    updateCourse.address = CourseHelper1.address
                    updateCourse.update(CourseHelper1.objectId,object : UpdateListener() {
                        override fun done(updateException: BmobException?) {
                            if (updateException != null) {
                                Log.e("UpdateCourse", "" + updateException.message)
                            }
                            else {
                                binding.detailCourseSelectedNum.text = updateCourse.selected_num.toString()
                                CourseHelper1.selected_num++
                                binding.selectOrGiveUp.isEnabled = true
                                binding.selectOrGiveUp.text = "退选"
                            }
                        }
                    })
                }
                else -> {
                    //TODO
                }
            }
        }
        binding.returnToMain.setOnClickListener {
            finish()
        }
    }
}