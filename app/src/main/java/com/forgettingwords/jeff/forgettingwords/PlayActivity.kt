package com.forgettingwords.jeff.forgettingwords

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.forgettingwords.jeff.forgettingwords.db.DatabaseHelper
import com.forgettingwords.jeff.forgettingwords.model.WordMeaning
import com.forgettingwords.jeff.forgettingwords.state.WordStateSt
import java.util.*
import java.nio.file.Files.size




class PlayActivity: AppCompatActivity() {

    private var chooseOption: Int = -1

    private lateinit var dbHelper: DatabaseHelper

    private lateinit var list: List<WordMeaning>
    val rng = Random()
    val randomValues = getRandomIntValues()

    fun ClosedRange<Int>.random() =
            Random().nextInt(endInclusive - start) + start

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

        list = WordStateSt.getPlayList()

        (if (list.size > 9) {
            playWord.setText(WordStateSt.getCurrentWord().meaning.toUpperCase())

            val idx = (0..3).random()

            val radioBtnList = listOf(radioButtonOpt1, radioButtonOpt2, radioButtonOpt3, radioButtonOpt4)



            radioBtnList.get(idx).setText(WordStateSt.getCurrentWord().name)

            radioBtnList.filterIndexed { index, radioButton ->
                index != idx
            }.forEachIndexed{ index, radioButton ->
                val idxF = randomValues.toList().get(index)
                val elem = list.get(idxF)
                radioButton.setText(elem.name)
            }

            checkButton.setOnClickListener { view ->
                val word = WordStateSt.getCurrentWord()
                if(chooseOption == -1 ||  radioBtnList.get(chooseOption).text != word.name)
                    word.errorAnswers = word.errorAnswers + 1
                else
                    word.rightAnswers = word.rightAnswers + 1

                word.percentage = (word.rightAnswers * 100.0) / (word.rightAnswers + word.errorAnswers)
                dbHelper.createOrUpdate(word)

                WordStateSt.currIndex = WordStateSt.currIndex + 1
                if(WordStateSt.currIndex < 9){
                    val intent = Intent(applicationContext, PlayActivity::class.java)
                    finish()
                    startActivity(intent)
                }
                else{
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }

            }
            checkButton.setBackgroundColor(Color.GREEN)
            checkButton.setTextColor(Color.WHITE)
            checkButton.setTypeface(null, Typeface.BOLD)
            playWord.setTextColor(Color.GREEN)
            playWord.setTypeface(null, Typeface.BOLD);
        } else {
            playWord.setText("You must add 10 words at least")
            playWord.setTextColor(Color.RED)
            playWord.setTypeface(null, Typeface.BOLD);
            checkButton.setOnClickListener { view ->
                Toast.makeText(applicationContext, "You must add 10 words at least", Toast.LENGTH_LONG).show()
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

    fun getRandomIntValues(): List<Int> {
        val generated = LinkedHashSet<Int>()

        while (generated.size < 9) {

            val next = rng.nextInt(9) + 1
            generated.add(next)
        }
        return generated.filter { num -> num !=  WordStateSt.currIndex}
    }


}