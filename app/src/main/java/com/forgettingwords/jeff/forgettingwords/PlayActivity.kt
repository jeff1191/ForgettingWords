package com.forgettingwords.jeff.forgettingwords

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import com.forgettingwords.jeff.forgettingwords.db.DatabaseHelper


class PlayActivity: Activity() {

    private var chooseOption: String = ""

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val checkButton = findViewById(R.id.checkButton) as Button
        val radioButtonOpt1 = findViewById(R.id.opt1) as RadioButton
        val radioButtonOpt2 = findViewById(R.id.opt2) as RadioButton
        val radioButtonOpt3 = findViewById(R.id.opt3) as RadioButton
        val radioButtonOpt4 = findViewById(R.id.opt4) as RadioButton


        radioButtonOpt1.setText("dsadsasd")
        radioButtonOpt2.setText("dsadsasd1")
        radioButtonOpt3.setText("dsadsasd2")
        radioButtonOpt4.setText("dsadsasd3")

        //init db
        dbHelper = DatabaseHelper(this)

        checkButton.setOnClickListener{view ->
            val intent = Intent(this, NewWordActivity::class.java)
            // To pass any data to next activity
//            intent.putExtra("keyIdentifier", "anyValue")
            Toast.makeText(applicationContext, chooseOption, Toast.LENGTH_LONG).show()
//            startActivity(intent)
        }
    }

    fun onRadioButtonClicked(view: View) {
        val checked = (view as RadioButton).isChecked

        when (view.getId()) {
            R.id.opt1 ->{
                if (checked)
                   chooseOption="opt1 saved  : )"

            }
            R.id.opt2 ->{
                if (checked)
                    chooseOption="opt2 saved  : )"
            }
            R.id.opt3 ->{
                if (checked)
                    chooseOption="opt3 saved  : )"
            }
            R.id.opt4 ->{
                if (checked)
                    chooseOption="opt4 saved  : )"
            }

        }


    }
}