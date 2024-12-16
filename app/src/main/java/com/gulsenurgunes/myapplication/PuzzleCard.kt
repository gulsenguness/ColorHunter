package com.gulsenurgunes.myapplication

data class PuzzleCard(
    val id: Int,
    val color: Int,
    var isFlipped: Boolean = false,
    var isMatched: Boolean = false
)
