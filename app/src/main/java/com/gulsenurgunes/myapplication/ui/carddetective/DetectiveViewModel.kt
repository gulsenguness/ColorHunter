package com.gulsenurgunes.myapplication.ui.carddetective

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gulsenurgunes.myapplication.data.detectivecard.DetectiveCard

class DetectiveViewModel(application: Application) : AndroidViewModel(application) {
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    private val _cards = MutableLiveData<List<DetectiveCard>>()
    val cards: LiveData<List<DetectiveCard>> get() = _cards

    private val _imageToMatch = MutableLiveData<Int?>()
    val imageToMatch: LiveData<Int?> get() = _imageToMatch

    private val _isGameReady = MutableLiveData(false)
    val isGameReady: LiveData<Boolean> get() = _isGameReady

    private val _selectedCards = MutableLiveData<List<DetectiveCard>>(emptyList())
    val selectedCards: LiveData<List<DetectiveCard>> get() = _selectedCards
    private val _usedImages = MutableLiveData<Set<Int>>(emptySet())
    val usedImages: Set<Int> get() = _usedImages.value ?: emptySet()

    fun setUsedImages(images: Set<Int>) {
        _usedImages.value = images
    }

    init {
        _score.value = 0
    }

    fun setGameReady(isReady: Boolean) {
        _isGameReady.value = isReady
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

    fun updateScore(newScore: Int) {
        _score.value = newScore
    }

    fun nextImageToMatch() {
        val availableImages = generateCards()
            .map { it.imageId }
            .filter { it !in usedImages }

        if (availableImages.isNotEmpty()) {
            val nextImage = availableImages.random()
            setImageToMatch(nextImage)
            val newUsedImages = usedImages.toMutableSet()
            newUsedImages.add(nextImage)
            setUsedImages(newUsedImages)
        } else {
            setImageToMatch(null)
        }
    }
}
