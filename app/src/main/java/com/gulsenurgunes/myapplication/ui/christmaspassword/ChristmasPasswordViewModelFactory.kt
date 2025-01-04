package com.gulsenurgunes.myapplication.ui.christmaspassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gulsenurgunes.myapplication.data.QuestionDao

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
