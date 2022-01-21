package com.example.courseselectiondemo.ui.student

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
import com.example.courseselectiondemo.Student
import com.example.courseselectiondemo.User
import com.example.courseselectiondemo.databinding.ActivityUpdateDataBinding

class UpdateDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_update_data)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_data)

    }

    override fun onResume() {
        super.onResume()
        val query = BmobQuery<Student>()
        query.addWhereEqualTo("id", User.id)
        query.findObjects(object : FindListener<Student>() {
            override fun done(list: List<Student>, e : BmobException?) {
                if (e == null) {
                    binding.studentId.text = list[0].id
                    binding.studentName.text = list[0].name
                    binding.studentPassword.setText(list[0].password)
                    binding.studentPhone.setText(list[0].phone)
                    binding.studentAddress.setText(list[0].address)
                }
            }
        })
        binding.studentUpdate.setOnClickListener {
            val query1 = BmobQuery<Student>()
            query1.addWhereEqualTo("id", binding.studentId.text)
            query1.findObjects(object : FindListener<Student>() {
                override fun done(list1 : List<Student>, e : BmobException?) {
                    if (e == null) {
                        val student = Student()
                        student.id = binding.studentId.text.toString()
                        student.name = binding.studentName.text.toString()
                        student.password = binding.studentPassword.text.toString()
                        student.phone = binding.studentPhone.text.toString()
                        student.address = binding.studentAddress.text.toString()
                        student.update(list1[0].objectId, object : UpdateListener() {
                            override fun done(ex: BmobException?) {
                                if (ex == null) {
                                    Toast.makeText(CourseSelectionApplication.context, "修改成功！", Toast.LENGTH_SHORT).show()
                                }
                                else {
                                    Toast.makeText(CourseSelectionApplication.context, "修改失败！", Toast.LENGTH_SHORT).show()
                                    Log.e("UpdateStudentData:", "" + ex.message)
                                }
                            }
                        })
                    }
                }
            })
        }
        binding.studentCancel.setOnClickListener {
            finish()
        }
    }
}