package com.forgettingwords.jeff.forgettingwords

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.support.design.widget.FloatingActionButton
import android.widget.Toast
import com.forgettingwords.jeff.forgettingwords.db.DatabaseHelper
import com.forgettingwords.jeff.forgettingwords.state.WordStateSt

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //init db
        dbHelper = DatabaseHelper(this)
    }
    fun play(view : View){
        val intent = Intent(this, PlayActivity::class.java)
        WordStateSt.init(dbHelper.getPlayWords())
        startActivity(intent)
    }
    fun seeList(view : View){
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }
    fun addNewWord(view : View){
        val intent = Intent(this, NewWordActivity::class.java)
        // To pass any data to next activity
//            intent.putExtra("keyIdentifier", "anyValue")
        startActivity(intent)
    }
}
