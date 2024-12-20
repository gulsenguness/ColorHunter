package com.gulsenurgunes.myapplication.ui.colorfulmaze

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import com.gulsenurgunes.myapplication.Cell
import com.gulsenurgunes.myapplication.ui.theme.NYTheme.padding

@Composable
fun colorfulMaze(maze: List<List<Cell>>, onCellClick: (Cell) -> Unit) {
    Column(modifier = Modifier.padding(padding.dimension16)) {
        maze.forEach { row ->
            Row {
                row.forEach { cell ->
                    MazeCell(cell = cell, onCellClick = onCellClick)
                }
            }
        }
    }
}

@Composable
fun MazeCell(cell: Cell, onCellClick: (Cell) -> Unit) {
    val backgroundColor = when {
        cell.isStart -> Color.Green
        cell.isEnd -> Color.Red
        cell.isWall -> Color.Black
        else -> Color.White
    }

    Box(
        modifier = Modifier
            .size(padding.dimension40)
            .background(backgroundColor, shape = RectangleShape)
            .border(padding.dimension1, Color.Gray)
            .clickable { onCellClick(cell) }
    )
}


fun generateMaze(rows: Int, cols: Int): List<List<Cell>> {
    val maze = MutableList(rows) { MutableList(cols) { Cell(0, 0) } }

    maze[0][0] = maze[0][0].copy(isStart = true)
    maze[rows - 1][cols - 1] = maze[rows - 1][cols - 1].copy(isEnd = true)

    for (i in 1 until rows - 1) {
        for (j in 1 until cols - 1) {
            if (Math.random() < 0.3) {
                maze[i][j] = maze[i][j].copy(isWall = true)
            }
        }
    }

    return maze
}
