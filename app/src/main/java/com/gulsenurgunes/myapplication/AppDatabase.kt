package com.gulsenurgunes.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Question::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun questionDao():QuestionDao
}