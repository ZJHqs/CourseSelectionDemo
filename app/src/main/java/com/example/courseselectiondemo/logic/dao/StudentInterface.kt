package com.example.courseselectiondemo.logic.dao

interface StudentInterface {
    fun query(id: String, password: String)
    fun add(name: String, password: String)
    fun delete(name: String)
}