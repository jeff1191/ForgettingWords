package com.forgettingwords.jeff.forgettingwords

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.forgettingwords.jeff.forgettingwords.db.DatabaseHelper
import com.forgettingwords.jeff.forgettingwords.model.WordMeaning
import java.util.*
import java.nio.file.Files.size




class PlayActivity: Activity() {

    private var chooseOption: Int = -1

    private lateinit var dbHelper: DatabaseHelper

    private lateinit var list: List<WordMeaning>


    var rng = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        dbHelper = DatabaseHelper(this)

        val checkButton = findViewById(R.id.checkButton) as Button
        val radioButtonOpt1 = findViewById(R.id.opt1) as RadioButton
        val radioButtonOpt2 = findViewById(R.id.opt2) as RadioButton
        val radioButtonOpt3 = findViewById(R.id.opt3) as RadioButton
        val radioButtonOpt4 = findViewById(R.id.opt4) as RadioButton
        val playWord = findViewById(R.id.playWord) as TextView

        list = dbHelper.getPlayWords()

        (if (list.size > 9) {
            playWord.setText(list.get(0).meaning.toUpperCase())
            fun ClosedRange<Int>.random() =
                    Random().nextInt(endInclusive - start) + start

            val idx = (0..3).random()

            val radioBtnList = listOf<RadioButton>(radioButtonOpt1, radioButtonOpt2, radioButtonOpt3, radioButtonOpt4)
            val randomValues = getRandomIntValues()


            radioBtnList.get(idx).setText(list.get(0).name)

            radioBtnList.filterIndexed { index, radioButton ->
                index != idx
            }.forEachIndexed{ index, radioButton ->
                if (!list.none()) {
                    val idxF = randomValues.toList().get(index)
                    val elem = list.get(idxF)
                    radioButton.setText(elem.name)
                } else
                    radioButton.setText("N/A")
            }

            checkButton.setOnClickListener { view ->

                Toast.makeText(applicationContext, chooseOption.toString(), Toast.LENGTH_LONG).show()
                //            startActivity(intent)
            }
            checkButton.setBackgroundColor(Color.GREEN)
            checkButton.setTextColor(Color.WHITE)
            checkButton.setTypeface(null, Typeface.BOLD)
            playWord.setTextColor(Color.GREEN)
            playWord.setTypeface(null, Typeface.BOLD);
        } else {
            playWord.setText("You must add at least 10 words")
            playWord.setTextColor(Color.RED)
            playWord.setTypeface(null, Typeface.BOLD);
            checkButton.setOnClickListener { view ->
                Toast.makeText(applicationContext, "You must add at least 10 words", Toast.LENGTH_LONG).show()
            }
            radioButtonOpt1.setText("N/A")
            radioButtonOpt2.setText("N/A")
            radioButtonOpt3.setText("N/A")
            radioButtonOpt4.setText("N/A")
            checkButton.setBackgroundColor(Color.RED)
            checkButton.setTextColor(Color.WHITE)
            checkButton.setTypeface(null, Typeface.BOLD)
        })

    }

    fun onRadioButtonClicked(view: View) {
        val checked = (view as RadioButton).isChecked

        when (view.getId()) {
            R.id.opt1 ->{
                if (checked)
                   chooseOption=0

            }
            R.id.opt2 ->{
                if (checked)
                    chooseOption=1
            }
            R.id.opt3 ->{
                if (checked)
                    chooseOption=2
            }
            R.id.opt4 ->{
                if (checked)
                    chooseOption=3
            }

        }


    }

    fun getRandomIntValues(): LinkedHashSet<Int>{
        val generated = LinkedHashSet<Int>()

        while (generated.size < 9) {

            val next = rng.nextInt(9) + 1
            // As we're adding to a set, this will automatically do a containment check
            if(next != 0)
                generated.add(next)
        }
        return generated
    }
}