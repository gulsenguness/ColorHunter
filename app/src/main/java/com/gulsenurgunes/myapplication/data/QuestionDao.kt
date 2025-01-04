package com.gulsenurgunes.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions ORDER BY isAnswered=0 LIMIT 1")
    fun getNextQuestion(): Question?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: Question)

    @Query("UPDATE questions SET isAnswered = 1 WHERE id = :id")
    suspend fun markQuestionAsAnswered(id: Int)
}