package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class GameViewModel : ViewModel() {
    // The current _word
    private val _word = MutableLiveData<String>()
    val word:LiveData<String>
    get() = word


    // The current _score
    private val _score = MutableLiveData<Int>()
    val score:LiveData<Int>
        get() = score

    // The list of words - the front of the list is the next _word to guess
    private lateinit var wordList: MutableList<String>

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish


    init {

        Log.i("GameViewModel", "GameViewModel created!")
        _word.value = ""
        _score.value = 0
    }
    companion object {

        // Time when the game is over
        private const val DONE = 0L

        // Countdown time interval
        private const val ONE_SECOND = 1000L

        // Total time for the game
        private const val COUNTDOWN_TIME = 60000L

    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }

    /**
     * Resets the list of words and randomizes the order
     */
    fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /** Methods for buttons presses **/

    fun onSkip() {
        if (!wordList.isEmpty()) {
            _score.value = (_score.value)?.minus(1)
        }
        nextWord()
    }

     fun onCorrect() {
        if (!wordList.isEmpty()) {
            _score.value = (_score.value)?.plus(1)
        }
        nextWord()
    }

    /**
     * Moves to the next _word in the list
     */
     fun nextWord() {
        if (wordList.isEmpty()) {
            onGameFinish()
        } else {
            //Select and remove a _word from the list
            _word.value = wordList.removeAt(0)
        }

    }
    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }
}
