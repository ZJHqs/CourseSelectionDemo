# 小学选课系统

## 用户划分

1. 学生
2. 教师
3. 管理员

## 用户功能需求划分

1. 学生

   **选课**

   **退选**

   查看全部课程

   查看课程详细信息

   查看个人已选课程

   查看教授课程的教师信息

   修改个人信息（家长电话、住址等）

2. 教师

   查看个人授课

   查看单门课程的学生信息

   修改个人信息

3. 管理员

   新增课程

   删除课程

   新增学生

   新增教师

   删除学生

   删除教师

## 数据库设计

1. 学生表

   | 字段     | 中文名   | 数据类型 | 限制 |
   | -------- | -------- | -------- | ---- |
   | sid      | 学号     | String   | 主键 |
   | name     | 学生名称 | String   |      |
   | password | 密码     | String   |      |
   | phone    | 电话号   | String   |      |
   | address  | 家庭住址 | String   |      |

2. 教师表

   | 字段     | 中文名   | 数据类型 | 限制 |
   | -------- | -------- | -------- | ---- |
   | tid      | 教师号   | String   | 主键 |
   | name     | 教师名称 | String   |      |
   | password | 密码     | String   |      |
   | phone    | 电话号   | String   |      |

   

3. 课程表

   | 字段         | 中文名       | 数据类型 | 限制 |
   | ------------ | ------------ | -------- | ---- |
   | cid          | 课程号       | String   | 主键 |
   | name         | 课程名       | String   |      |
   | tid          | 教师号       | String   | 外键 |
   | selected_num | 已选课程人数 | int      |      |
   | max_num      | 课程容量     | int      |      |

   

4. 选课表

   | 字段 | 中文名 | 数据类型 | 限制 |
   | ---- | ------ | -------- | ---- |
   | sid  | 学生号 | String   | 主键 |
   | cid  | 课程号 | String   | 主键 |

## 功能实现

### 登录功能	√

1. 学生登录

   用例描述：学生输入账号与密码，并且身份选择学生。

   具体实现：通过账号与密码查询是否存在这样一个对象，如果有，跳转到`StudentMainActivity`

   实现代码：

   ```kotlin
   binding.login.setOnClickListener {
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
   }
   ```

   

2. 教师登录

   ```kotlin
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
   ```

   

3. 管理员登录

   ```kotlin
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
   ```

### 学生功能

1. 查看全部课程 √

   ```kotlin
   val bmobQuery = BmobQuery<Course1>()
           bmobQuery.findObjects(object : FindListener<Course1>() {
               override fun done(list: List<Course1>, e : BmobException?) {
                   if (e == null) {
                       val layoutManager = LinearLayoutManager(this@ShowCourse1)
                       val recyclerView : RecyclerView = findViewById(R.id.courseRecyclerView1)
                       recyclerView.layoutManager = layoutManager
                       val adapter = CourseAdapter1(list)
                       recyclerView.adapter = adapter
                       adapter.setOnItemCLickListener(object : CourseAdapter1.OnItemClickListener {
                           override fun onClick(position: Int) {
                               Toast.makeText(CourseSelectionApplication.context, "您点击的是 $position 行！", Toast.LENGTH_SHORT).show()
                           }
                       })
                   }
               }
           })
   ```

   

2. 查看课程详细信息&&查看授课教师信息 √

   | 课程名 | 教师名称 | 已选人数 | 课程容量 | 教师电话信息 | 上课地点 |
   | ------ | -------- | -------- | -------- | ------------ | -------- |
   |        |          |          |          |              |          |

   ```Kotlin
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
   ```

   

3. 查看个人选课信息 √

   ```kotlin
   package com.example.courseselectiondemo.ui.student
   
   import androidx.appcompat.app.AppCompatActivity
   import android.os.Bundle
   import android.widget.Toast
   import androidx.databinding.DataBindingUtil
   import androidx.recyclerview.widget.LinearLayoutManager
   import androidx.recyclerview.widget.RecyclerView
   import cn.bmob.v3.BmobQuery
   import cn.bmob.v3.exception.BmobException
   import cn.bmob.v3.listener.FindListener
   import com.example.courseselectiondemo.*
   import com.example.courseselectiondemo.databinding.ActivityShowCourse1Binding
   import com.example.courseselectiondemo.ui.CourseAdapter1
   
   class ShowSelectedCourseActivity : AppCompatActivity() {
   
       private lateinit var binding: ActivityShowCourse1Binding
   
       override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)
   //        setContentView(R.layout.activity_show_selected_course)
           binding = DataBindingUtil.setContentView(this, R.layout.activity_show_course1)
           val bmobQuery = BmobQuery<CourseSelection>()
           bmobQuery.addWhereEqualTo("sid", User.id)
           bmobQuery.findObjects(object : FindListener<CourseSelection>() {
               override fun done(list : List<CourseSelection>, e : BmobException?) {
                   if (e == null) {
                       if (list.isNotEmpty()) {
                           val query = BmobQuery<Course1>()
                           query.addWhereEqualTo("cid", list[0].cid)
                           query.findObjects(object : FindListener<Course1>() {
                               override fun done(list: List<Course1>, e : BmobException?) {
                                   if (e == null) {
                                       val layoutManager = LinearLayoutManager(this@ShowSelectedCourseActivity)
                                       val recyclerView : RecyclerView = findViewById(R.id.courseRecyclerView1)
                                       recyclerView.layoutManager = layoutManager
                                       val adapter = CourseAdapter1(list)
                                       recyclerView.adapter = adapter
                                   }
                               }
                           })
                       }
                       else {
                           Toast.makeText(CourseSelectionApplication.context, "您还未选课！", Toast.LENGTH_SHORT).show()
                       }
                   }
               }
           })
   
       }
   }
   ```

   

4. 