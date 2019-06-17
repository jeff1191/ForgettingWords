package com.forgettingwords.jeff.forgettingwords

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.forgettingwords.jeff.forgettingwords.db.DatabaseHelper

class ManagerActivity : Activity() {
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager)
        //init db
        dbHelper = DatabaseHelper(this)
    }

    fun showList(view : View){
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }
    fun addNewWordMeaning(view : View){
        val intent = Intent(this, NewWordActivity::class.java)
        // To pass any data to next activity
//            intent.putExtra("keyIdentifier", "anyValue")
        startActivity(intent)
    }
    fun addNewIrregularVerb(view : View){
        val intent = Intent(this, NewWordActivity::class.java)
        // To pass any data to next activity
//            intent.putExtra("keyIdentifier", "anyValue")
        startActivity(intent)
    }
    fun addNewPhrasalVerb(view : View){
        val intent = Intent(this, NewWordActivity::class.java)
        // To pass any data to next activity
//            intent.putExtra("keyIdentifier", "anyValue")
        startActivity(intent)
    }

}
