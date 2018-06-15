package com.forgettingwords.jeff.forgettingwords.state

import com.forgettingwords.jeff.forgettingwords.model.WordMeaning
import java.util.*

object WordStateSt {
    var currIndex: Int = 0
    private lateinit var wordState: List<WordMeaning>

    fun init(newState: List<WordMeaning>){
        wordState = newState
        currIndex = 0
    }


    fun getPlayList(): List<WordMeaning> {
        return wordState
    }
    fun getCurrentWord(): WordMeaning {
        return wordState.get(currIndex)
    }

}