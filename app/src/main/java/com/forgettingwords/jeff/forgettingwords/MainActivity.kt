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
    fun playWordMeaning(view : View){
        val intent = Intent(this, PlayActivity::class.java)
        WordStateSt.init(dbHelper.getPlayWords())
        startActivity(intent)
    }
    fun playIrregularGame(view : View){
        val intent = Intent(this, PlayActivity::class.java)
        WordStateSt.init(dbHelper.getPlayWords())
        startActivity(intent)
    }
    fun playPhrasalVerb(view : View){
        val intent = Intent(this, PlayActivity::class.java)
        WordStateSt.init(dbHelper.getPlayWords())
        startActivity(intent)
    }
    fun showUrbanWords(view : View){
        val intent = Intent(this, UrbanActivity::class.java)
        WordStateSt.init(dbHelper.getPlayWords())
        startActivity(intent)
    }
    fun showManager(view : View){
        val intent = Intent(this, ManagerActivity::class.java)
        WordStateSt.init(dbHelper.getPlayWords())
        startActivity(intent)
    }

}
