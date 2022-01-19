package com.example.courseselectiondemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.courseselectiondemo.Course1
import com.example.courseselectiondemo.R
import com.example.courseselectiondemo.Teacher

class CourseAdapter1(val courseList1: List<Course1>) :
    RecyclerView.Adapter<CourseAdapter1.ViewHolder>(){
        inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
            val cname : TextView = view.findViewById(R.id.cname1)
            val tid : TextView = view.findViewById(R.id.tid1)
            val selected_num1 : TextView = view.findViewById(R.id.selected_num1)
            val max_num1 : TextView = view.findViewById(R.id.max_num1)
        }

    lateinit var mOnItemClickListener: OnItemClickListener
    interface OnItemClickListener {
        fun onClick(position: Int)
    }
    fun setOnItemCLickListener(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course1 = courseList1[position]
        holder.cname.text = course1.name
        val query = BmobQuery<Teacher>()
        query.addWhereEqualTo("id", course1.tid)
        query.findObjects(object : FindListener<Teacher>() {
            override fun done(list : List<Teacher>, e : BmobException?) {
                if (e == null) {
                    holder.tid.text = list[0].name
                }
            }
        })
//        holder.tid.text = course1.tid
        holder.selected_num1.text = course1.selected_num.toString()
        holder.max_num1.text = course1.max_num.toString()
        holder.itemView.setOnClickListener {
            mOnItemClickListener.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return courseList1.size
    }
}