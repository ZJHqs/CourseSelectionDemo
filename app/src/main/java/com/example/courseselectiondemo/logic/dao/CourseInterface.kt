package com.example.courseselectiondemo.logic.dao

interface CourseInterface {
    fun add(name: String, type: String, credit: Double)
//    fun findObjectIdByName(name: String) : String
    suspend fun writeObjectIdByName(name: String)
//    fun getObjectIdFromHelper() : String
}