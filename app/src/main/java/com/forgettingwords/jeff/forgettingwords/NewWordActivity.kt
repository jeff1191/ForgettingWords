package com.forgettingwords.jeff.forgettingwords

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.EditText
import android.widget.Toast
import com.forgettingwords.jeff.forgettingwords.db.DatabaseHelper
import com.forgettingwords.jeff.forgettingwords.model.WordMeaning

class NewWordActivity: Activity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newword)

        //init db
        dbHelper = DatabaseHelper(this)

        val newWord = findViewById(R.id.saveButtonNewWord) as FloatingActionButton
        val returnButton= findViewById(R.id.returnButtonNewWord) as FloatingActionButton
        val newWordText = findViewById<EditText>(R.id.newWordText)
        val newWordMeaning = findViewById<EditText>(R.id.newWordMeaning)

        newWord.setOnClickListener{view ->

            if(newWordText.text.isEmpty() || newWordMeaning.text.isEmpty())
                Toast.makeText(applicationContext, "Word or Meaning Empty  : (", Toast.LENGTH_LONG).show()
            else{
                Toast.makeText(applicationContext, "${newWordText.text.toString()} saved  : )", Toast.LENGTH_LONG).show()
                dbHelper.createOrUpdate(WordMeaning( name = newWordText.text.toString(), meaning = newWordMeaning.text.toString()))

                newWordText.setText("")
                newWordMeaning.setText("")
            }
        }

        returnButton.setOnClickListener{view ->
            finish()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        dbHelper.connectionSource.close()
    }

}