package com.example.courseselectiondemo.ui.teacher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.UpdateListener
import com.example.courseselectiondemo.CourseSelectionApplication
import com.example.courseselectiondemo.R
import com.example.courseselectiondemo.Teacher
import com.example.courseselectiondemo.User
import com.example.courseselectiondemo.databinding.ActivityUpdateTeacherDataBinding

class UpdateTeacherData : AppCompatActivity() {

    private lateinit var binding : ActivityUpdateTeacherDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_update_teacher_data)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_teacher_data)
        binding.teacherCancel.setOnClickListener {
            finish()
        }
        binding.teacherUpdate.setOnClickListener {
            val query = BmobQuery<Teacher>()
            query.addWhereEqualTo("id", User.id)
            query.findObjects(object : FindListener<Teacher>() {
                override fun done(list : List<Teacher>, e : BmobException?) {
                    if (e == null) {
                        val objectId = list[0].objectId
                        val teacher = Teacher()
                        teacher.id = binding.teacherId.text.toString()
                        teacher.name = binding.teacherName.text.toString()
                        teacher.password = binding.teacherPassword.text.toString()
                        teacher.phone = binding.teacherPhone.text.toString()
                        teacher.update(objectId, object : UpdateListener() {
                            override fun done(e1 : BmobException?) {
                                if (e1 == null) {
                                    Toast.makeText(CourseSelectionApplication.context, "修改成功！", Toast.LENGTH_SHORT).show()
                                }
                                else {
                                    Toast.makeText(CourseSelectionApplication.context, "修改失败！", Toast.LENGTH_SHORT).show()
                                    Log.e("UpdateStudentData:", "" + e1.message)
                                }
                            }
                        })
                    }
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
//        binding.teacherId.text = User.id
        val query = BmobQuery<Teacher>()
        query.addWhereEqualTo("id", User.id)
        query.findObjects(object : FindListener<Teacher>() {
            override fun done(list : List<Teacher>, e : BmobException?) {
                if (e == null) {
                    binding.teacherId.text = list[0].id
                    binding.teacherName.text = list[0].name
                    binding.teacherPassword.setText(list[0].password)
                    binding.teacherPhone.setText(list[0].phone)
                }
            }
        })
    }
}