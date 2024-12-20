package com.gulsenurgunes.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memory_cards")
data class DetectiveCard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imageId: Int,
    var isFaceUp: Boolean ,
    var isMatched: Boolean,
    val level:Int,
)
