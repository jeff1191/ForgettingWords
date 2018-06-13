package com.forgettingwords.jeff.forgettingwords

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.EditText
import android.widget.Toast

class NewWordActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newword)


        val newWord = findViewById(R.id.saveButtonNewWord) as FloatingActionButton

        newWord.setOnClickListener{view ->

            val newWordText = findViewById(R.id.newWordText) as EditText
            val newWordMeaning = findViewById(R.id.newWordMeaning) as EditText

            Toast.makeText(applicationContext, "${newWordText.text} Saved", Toast.LENGTH_LONG).show()
            newWordText.setText("")
            newWordMeaning.setText("")
        }
        val returnButton= findViewById(R.id.returnButtonNewWord) as FloatingActionButton

        returnButton.setOnClickListener{view ->

            finish();
        }
    }
}