package com.gulsenurgunes.myapplication.ui.christmaspassword

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulsenurgunes.myapplication.R
import com.gulsenurgunes.myapplication.data.Question
import com.gulsenurgunes.myapplication.data.QuestionDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChristmasPasswordViewModel(
    private val questionDao: QuestionDao
) : ViewModel() {
    private val _currentQuestion = MutableLiveData<Question?>()
    val currentQuestion: LiveData<Question?> = _currentQuestion

    private val _puzzleState = MutableLiveData<List<Int?>?>()
    val puzzleState: MutableLiveData<List<Int?>?> = _puzzleState

    private val clickCountMap = mutableMapOf<Int, Int>()

    var score = MutableLiveData(0)

    private val allPuzzlePieces = listOf(
        R.drawable.noelhediye, R.drawable.noel1, R.drawable.noel2,
        R.drawable.noel3, R.drawable.noel4, R.drawable.noel7,
        R.drawable.noel8, R.drawable.noel9, R.drawable.tree
    )

    init {
        loadNextQuestion()
        initializePuzzle()
        insertSampleQuestions()
    }

    private fun initializePuzzle() {
        _puzzleState.value = allPuzzlePieces.map { null }
    }

    private fun loadNextQuestion() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val nextQuestion = questionDao.getNextQuestion()
                nextQuestion?.let {
                    _currentQuestion.postValue(it)
                    clickCountMap[it.id]=0
                }
            } catch (_: Exception) {
            }
        }
    }

    private fun insertSampleQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            val sampleQuestions = listOf(
                Question(
                    id = 1,
                    text = "Noel Baba'nın kullandığı hayvan nedir?",
                    option = listOf("Kedi", "Köpek", "Geyik"),
                    correctAnswer = "Geyik",
                ),
                Question(
                    id = 2,
                    text = "Noel ağacına hangi süsler asılır?",
                    option = listOf("Çorap", "Toplar ve süsler", "Ayakkabılar"),
                    correctAnswer = "Toplar ve süsler"
                ),
                Question(
                    id = 3,
                    text = "Noel Baba'nın giydiği kıyafet rengi nedir?",
                    option = listOf("Beyaz", "Kırmızı", "Mavi"),
                    correctAnswer = "Kırmızı"
                ),
                Question(
                    id = 4,
                    text = "Noel Baba'nın yardımcıları hangi tür varlıklardır?",
                    option = listOf("Elfler", "İnsanlar", "Hayvanlar"),
                    correctAnswer = "Elfler"
                ),
                Question(
                    id = 5,
                    text = "Noel'de hangi tatlı yaygın olarak yenir?",
                    option = listOf("Yılbaşı keki", "Pizza", "Çorba"),
                    correctAnswer = "Yılbaşı keki"
                ),
                Question(
                    id = 6,
                    text = "Noel Baba'nın kızağını hangi hayvanlar çeker?",
                    option = listOf("Koyun", "Ren geyiği", "At"),
                    correctAnswer = "Ren geyiği"
                ),
                Question(
                    id = 7,
                    text = "Noel günü hangi ünlü şarkı söylenir?",
                    option = listOf("Happy Birthday", "Jingle Bells", "Silent Night"),
                    correctAnswer = "Jingle Bells"
                ),
                Question(
                    id = 8,
                    text = "Noel Baba'nın yaşadığı yer nerededir?",
                    option = listOf("Kuzey Kutbu", "Alpler", "Hindistan"),
                    correctAnswer = "Kuzey Kutbu"
                ),
                Question(
                    id = 9,
                    text = "Noel'de hangi renk en çok kullanılır?",
                    option = listOf("Sarı", "Kırmızı", "Mavi"),
                    correctAnswer = "Kırmızı"
                ),
                Question(
                    id = 10,
                    text = "Noel Baba'nın yardımcısı kimdir?",
                    option = listOf("Kırmızı Şapka", "Elfler", "Yılbaşı Ağacı"),
                    correctAnswer = "Elfler"
                )
            )

            for (question in sampleQuestions) {
                questionDao.insertQuestions(question)
            }
        }
    }

    fun onAnswerSelected(answer: String) {
        viewModelScope.launch {
            val question = _currentQuestion.value
            if (question != null) {
                val currentClickCount = clickCountMap[question.id] ?: 0
                val isCorrect = answer == question.correctAnswer

                clickCountMap[question.id] = currentClickCount + 1

                if (isCorrect) {
                    val points = when (currentClickCount) {
                        0 -> 50
                        1 -> 30
                        else -> 10
                    }

                    score.postValue(score.value?.plus(points))
                    questionDao.markQuestionAsAnswered(question.id)

                    val updatedState = _puzzleState.value?.toMutableList()
                    val nextClosedIndex = updatedState?.indexOfFirst { it == null }
                    if (nextClosedIndex != null && nextClosedIndex >= 0) {
                        updatedState[nextClosedIndex] = allPuzzlePieces[nextClosedIndex]
                    }
                    _puzzleState.postValue(updatedState)

                    loadNextQuestion()
                } else {
                    Log.d("ChristmasPassword", "Wrong Answer! Question ID: ${question.id}, Current Click: $currentClickCount")
                }
            }
        }
    }

}
