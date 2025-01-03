package com.gulsenurgunes.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChristmasPasswordViewModelFactory(
    private val questionDao: QuestionDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChristmasPasswordViewModel::class.java)) {
            return ChristmasPasswordViewModel(questionDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
