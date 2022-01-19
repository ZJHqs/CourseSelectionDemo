package com.example.courseselectiondemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.SaveListener
import cn.bmob.v3.listener.UpdateListener
import com.example.courseselectiondemo.databinding.ActivityMainBinding
import com.example.courseselectiondemo.logic.dao.StudentImpl
import com.example.courseselectiondemo.logic.dao.TeacherImpl
import com.example.courseselectiondemo.ui.ShowCourse
import com.example.courseselectiondemo.ui.admin.AdminMainActivity
import com.example.courseselectiondemo.ui.student.StudentMainActivity
import com.example.courseselectiondemo.ui.teacher.TeacherMainActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Bmob.initialize(CourseSelectionApplication.context, "b376a5290dd966c9d1444e85117e42d4")
        binding.login.setOnClickListener {
            User.id = binding.uid.text.toString()
            if (binding.student.isChecked) {
                val bmobQuery1: BmobQuery<Student> = BmobQuery()
                val bmobQuery2: BmobQuery<Student> = BmobQuery()
                bmobQuery1.addWhereEqualTo("id", binding.uid.text.toString())
                bmobQuery2.addWhereEqualTo("password", binding.upassword.text.toString())
                val queries = ArrayList<BmobQuery<Student>>()
                queries.add(bmobQuery1)
                queries.add(bmobQuery2)
                val bmobQuery = BmobQuery<Student>()
                bmobQuery.and(queries)
                bmobQuery.findObjects(object : FindListener<Student>() {
                    override fun done(list: List<Student>, e: BmobException?){
                        if (e == null) {
                            if (list.isNotEmpty()) {
                                val intent = Intent(this@MainActivity, StudentMainActivity::class.java)
                                startActivity(intent)
                            }
                            else {
                                Toast.makeText(CourseSelectionApplication.context, "请输入正确的账号和密码！", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else {
                            Log.e("BMOB", e.toString())
                        }
                    }
                })
            }
            else if (binding.teacher.isChecked) {
                val bmobQuery1: BmobQuery<Teacher> = BmobQuery()
                val bmobQuery2: BmobQuery<Teacher> = BmobQuery()
                bmobQuery1.addWhereEqualTo("id", binding.uid.text.toString())
                bmobQuery2.addWhereEqualTo("password", binding.upassword.text.toString())
                val queries = ArrayList<BmobQuery<Teacher>>()
                queries.add(bmobQuery1)
                queries.add(bmobQuery2)
                val bmobQuery = BmobQuery<Teacher>()
                bmobQuery.and(queries)
                bmobQuery.findObjects(object : FindListener<Teacher>() {
                    override fun done(list: List<Teacher>, e: BmobException?){
                        if (e == null) {
                            if (list.isNotEmpty()) {
                                val intent = Intent(this@MainActivity, TeacherMainActivity::class.java)
                                startActivity(intent)
                            }
                            else {
                                Toast.makeText(CourseSelectionApplication.context, "请输入正确的账号和密码！", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else {
                            Log.e("BMOB", e.toString())
                        }
                    }
                })
            }
            else if (binding.admin.isChecked) {
                val bmobQuery1: BmobQuery<Admin> = BmobQuery()
                val bmobQuery2: BmobQuery<Admin> = BmobQuery()
                bmobQuery1.addWhereEqualTo("objectId", binding.uid.text.toString())
                bmobQuery2.addWhereEqualTo("password", binding.upassword.text.toString())
                val queries = ArrayList<BmobQuery<Admin>>()
                queries.add(bmobQuery1)
                queries.add(bmobQuery2)
                val bmobQuery = BmobQuery<Admin>()
                bmobQuery.and(queries)
                bmobQuery.findObjects(object : FindListener<Admin>() {
                    override fun done(list: List<Admin>, e: BmobException?) {
                        if (e == null) {
                            if (list.isNotEmpty()) {
                                val intent = Intent(this@MainActivity, AdminMainActivity::class.java)
                                startActivity(intent)
                            }
                            else {
                                Toast.makeText(CourseSelectionApplication.context, "请输入正确的账号和密码！", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else {
                            Log.e("BMOB", e.toString())
                        }
                    }
                })
            }
            else {
                Toast.makeText(CourseSelectionApplication.context, "Error!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.button.setOnClickListener {
//            val student = Student()
//            student.id = "123"
//            student.password = "123"
//            student.name = "ZJH"
//            student.phone = "10010"
//            student.update("TNcZX77X", object : UpdateListener() {
//                override fun done(e: BmobException?) {
//                    if (e != null) {
//                        Toast.makeText(CourseSelectionApplication.context, "修改失败！", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            })
            val intent = Intent(this, ShowCourse::class.java)
            startActivity(intent)
//            for (i in 1..30) {
//                val teacher = Teacher()
//                teacher.id = (10000 + i).toString()
//                teacher.name = "张三"
//                teacher.password = "123456"
//                teacher.save(object : SaveListener<String>() {
//                    override fun done(objectId : String?, e : BmobException?) {
//                        if (e != null) {
//                            Log.e("MainActivity:","ERROR!")
//                        }
//                    }
//                })
//                val course1 = Course1()
//                course1.cid = "c" + (100000 + i)
//                course1.tid = (10000 + i).toString()
//                course1.selected_num = 0
//                course1.max_num = 50
//                course1.save(object : SaveListener<String>() {
//                    override fun done(objectId : String?, e : BmobException?) {
//                        if (e != null) {
//                            Log.e("MainActivity:", "Error!")
//                        }
//                    }
//                })
//            }
        }
    }

}
