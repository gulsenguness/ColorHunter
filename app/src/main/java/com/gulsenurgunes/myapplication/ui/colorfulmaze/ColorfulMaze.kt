package com.gulsenurgunes.myapplication.ui.colorfulmaze

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp


//Deneme
@Composable
fun colorfulMaze(maze:List<List<Cell>>) {
    Column(modifier = Modifier.padding(16.dp)) {
        maze.forEach { row ->
            Row {
                row.forEach { cell ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                if (cell.isWall) Color.Black else Color.White,
                                shape = RectangleShape
                            )
                            .border(1.dp, Color.Gray)
                    )
                }
            }
        }
    }
}

data class Cell(val row: Int, val col: Int, val isWall: Boolean)



