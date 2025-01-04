package com.gulsenurgunes.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions WHERE isAnswered=0 ORDER BY RANDOM() LIMIT 1")
    fun getNextQuestion(): Question?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: Question)

    @Query("UPDATE questions SET isAnswered = 1 WHERE id = :id")
    suspend fun markQuestionAsAnswered(id: Int)
}