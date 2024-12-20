package com.gulsenurgunes.myapplication

data class Cell(
    val row: Int,
    val col: Int,
    val isWall: Boolean = false,
    val isStart: Boolean = false,
    val isEnd: Boolean = false,
)
