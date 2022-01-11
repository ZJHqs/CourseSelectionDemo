package com.example.courseselectiondemo

interface AdminInterface {
    fun query(id: String, password: String)
    fun add(name: String, password: String)
    fun delete(id: String)
}