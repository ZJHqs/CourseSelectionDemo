package com.example.courseselectiondemo.ui.teacher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.courseselectiondemo.CourseHelper1
import com.example.courseselectiondemo.CourseSelection
import com.example.courseselectiondemo.R
import com.example.courseselectiondemo.Student
import com.example.courseselectiondemo.databinding.ActivityShowTeachingStudentBinding
import com.example.courseselectiondemo.ui.StudentAdapter

class ShowTeachingStudent : AppCompatActivity() {

    private lateinit var binding: ActivityShowTeachingStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_teaching_student)
    }

    override fun onResume() {
        super.onResume()
        /**
         * 通过cid找到所有sid
         */
        val query1 = BmobQuery<CourseSelection>()
        query1.addWhereEqualTo("cid", CourseHelper1.cid)
        query1.findObjects(object : FindListener<CourseSelection>() {
            override fun done(list1: List<CourseSelection>, e1 : BmobException?) {
                if (e1 == null) {
                    /**
                     * 找出全部学生
                     */
                    val query2 = BmobQuery<Student>()
                    query2.findObjects(object : FindListener<Student>() {
                        override fun done(list2 : List<Student>, e2 : BmobException?) {
                            if (e2 == null) {
                                /**
                                 * 将所有选该们课程的学生的学生号放入哈希表中
                                 */
                                val list = ArrayList<Student>()
                                val set = HashSet<String>()
                                for (courseSelection in list1) {
                                    set.add(courseSelection.sid)
                                }
                                /**
                                 * 将需要读出来的Student放入list中
                                 * list就是我们最终呈现的内容
                                 */
                                for (student in list2) {
                                    if (set.contains(student.id)) {
                                        list.add(student)
                                    }
                                }
                                val layoutManager = LinearLayoutManager(this@ShowTeachingStudent)
                                val recyclerView : RecyclerView = findViewById(R.id.student_recyclerview)
                                recyclerView.layoutManager = layoutManager
                                val adapter = StudentAdapter(list)
                                recyclerView.adapter = adapter
                            }
                        }
                    })
                }
            }
        })
    }
}