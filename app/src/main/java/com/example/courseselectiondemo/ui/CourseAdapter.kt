package com.example.courseselectiondemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.courseselectiondemo.Course
import com.example.courseselectiondemo.R

class CourseAdapter(val courseList: List<Course>) :
    RecyclerView.Adapter<CourseAdapter.ViewHolder>(){
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val cname : TextView = view.findViewById(R.id.cname)
            val credit : TextView = view.findViewById(R.id.credit)
            val type : TextView = view.findViewById(R.id.type)
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
            .inflate(R.layout.course, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = courseList[position]
        holder.cname.text = course.name
        holder.credit.text = course.credit.toString()
        holder.type.text = course.type
        holder.itemView.setOnClickListener { mOnItemClickListener.onClick(position) }
    }

    override fun getItemCount(): Int {
        return courseList.size
    }
}
