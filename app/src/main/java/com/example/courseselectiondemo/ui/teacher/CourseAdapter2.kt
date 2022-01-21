package com.example.courseselectiondemo.ui.teacher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.courseselectiondemo.Course1
import com.example.courseselectiondemo.R

class CourseAdapter2(val courseList : List<Course1>) :
    RecyclerView.Adapter<CourseAdapter2.ViewHolder>(){
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val cname : TextView = view.findViewById(R.id.cname2)
            val select_num : TextView = view.findViewById(R.id.selected_num2)
            val max_num : TextView = view.findViewById(R.id.max_num2)
            val address : TextView = view.findViewById(R.id.address2)
        }
    lateinit var mOnItemClickListener: OnItemClickListener
    interface OnItemClickListener {
        fun onClick(position: Int)
    }
    fun setOnItemCLickListener(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).
                inflate(R.layout.course2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = courseList[position]
        holder.cname.text = course.name
        holder.select_num.text = course.selected_num.toString()
        holder.max_num.text = course.max_num.toString()
        holder.address.text = course.address
        holder.itemView.setOnClickListener {
            mOnItemClickListener.onClick(position)
        }

    }

    override fun getItemCount(): Int {
        return courseList.size
    }

}