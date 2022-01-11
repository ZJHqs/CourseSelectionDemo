package com.example.courseselectiondemo.logic.dao

interface TeacherInterface {
    fun query(id: String, password: String)
    fun add(name: String, password: String)
    fun delete(id: String)
}