package com.gulsenurgunes.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DetectiveViewModel(application: Application) : AndroidViewModel(application) {
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score
    private val _cards = MutableLiveData<List<DetectiveCard>>()
    val cards: LiveData<List<DetectiveCard>> get() = _cards
    private val _imageToMatch = MutableLiveData<Int?>()
    val imageToMatch: LiveData<Int?> get() = _imageToMatch

    private val _selectedCards = MutableLiveData<List<DetectiveCard>>(emptyList())
    val selectedCards: LiveData<List<DetectiveCard>> get() = _selectedCards

    init {
        _score.value = 0
    }

    fun updateScore(newScore: Int) {
        _score.value = newScore
    }

    fun setImageToMatch(imageId: Int?) {
        _imageToMatch.value = imageId
    }

    fun setCards(cardsList: List<DetectiveCard>) {
        _cards.value = cardsList
    }

    fun addSelectedCard(card: DetectiveCard) {
        val newSelectedCards = _selectedCards.value?.toMutableList() ?: mutableListOf()
        newSelectedCards.add(card)
        _selectedCards.value = newSelectedCards
    }

    fun resetSelectedCards() {
        _selectedCards.value = emptyList()
    }

    fun resetGame() {
        _score.value = 0
        resetSelectedCards()
    }
}
