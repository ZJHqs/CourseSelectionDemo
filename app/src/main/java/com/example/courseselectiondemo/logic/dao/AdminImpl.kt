package com.example.courseselectiondemo.logic.dao

import android.util.Log
import android.widget.Toast
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import cn.bmob.v3.listener.SaveListener
import cn.bmob.v3.listener.UpdateListener
import com.example.courseselectiondemo.Admin
import com.example.courseselectiondemo.AdminInterface
import com.example.courseselectiondemo.CourseSelectionApplication


class AdminImpl : AdminInterface {
    override fun query(id: String, password: String) {
        val bmobQuery1: BmobQuery<Admin> = BmobQuery()
        val bmobQuery2: BmobQuery<Admin> = BmobQuery()
        bmobQuery1.addWhereEqualTo("objectId", id)
        bmobQuery2.addWhereEqualTo("password", password)
        val queries = ArrayList<BmobQuery<Admin>>()
        queries.add(bmobQuery1)
        queries.add(bmobQuery2)
        val bmobQuery = BmobQuery<Admin>()
        bmobQuery.and(queries)
        bmobQuery.findObjects(object : FindListener<Admin>() {
            override fun done(list: List<Admin>, e: BmobException?) {
                if (e == null) {
                    if (list.isNotEmpty()) {
                        Toast.makeText(CourseSelectionApplication.context, "有该用户", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(CourseSelectionApplication.context, "该用户不存在！", Toast.LENGTH_SHORT).show()
                    }
//                    Log.d("QUERY", ""+list.size)
                }
                else {
                    Log.e("BMOB", e.toString())
                }
            }
        })
    }

    override fun add(name: String, password: String) {
        val admin = Admin()
        admin.name = name
        admin.password = password
        admin.save(object : SaveListener<String>() {
            override fun done(objectId: String?, e : BmobException?) {
                if (e != null) {
                    Log.e("CREATE","FAILED!"+ e.message)
                }
            }
        })
    }

    override fun delete(id: String) {
        val admin = Admin()
        admin.objectId = id
        admin.delete(object : UpdateListener() {
            override fun done(e: BmobException?) {
                if (e == null) {
                    Toast.makeText(CourseSelectionApplication.context, "删除成功！", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(CourseSelectionApplication.context, "该用户不存在", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}