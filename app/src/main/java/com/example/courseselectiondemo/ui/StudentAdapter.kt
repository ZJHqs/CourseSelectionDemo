package com.example.courseselectiondemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.courseselectiondemo.R
import com.example.courseselectiondemo.Student

class StudentAdapter(val studentList: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.ViewHolder>(){
        inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
            val id : TextView = view.findViewById(R.id.student_id1)
            val name : TextView = view.findViewById(R.id.student_name1)
            val phone : TextView = view.findViewById(R.id.student_phone1)
            val address : TextView = view.findViewById(R.id.student_address1)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = studentList[position]
        holder.id.text = student.id
        holder.name.text = student.name
        holder.phone.text = student.phone
        holder.address.text = student.address
    }

    override fun getItemCount(): Int = studentList.size
}