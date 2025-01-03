package com.gulsenurgunes.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    val option: List<String>,
    val correctAnswer: String,
    val isAnswered: Boolean = false
)
