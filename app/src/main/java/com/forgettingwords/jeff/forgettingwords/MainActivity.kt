package com.forgettingwords.jeff.forgettingwords

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.support.design.widget.FloatingActionButton
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newWord = findViewById(R.id.newWord) as FloatingActionButton

        newWord.setOnClickListener{view ->
            val intent = Intent(this, NewWordActivity::class.java)
            // To pass any data to next activity
            intent.putExtra("keyIdentifier", "anyValue")
            // start your next activity
            startActivity(intent)
        }
        val quit= findViewById(R.id.quitButton) as FloatingActionButton

        quit.setOnClickListener{view ->
            Toast.makeText(applicationContext, "Bye : (", Toast.LENGTH_LONG).show()
            finish();
        }
    }
    fun play(view : View){
        val intent = Intent(this, PlayActivity::class.java)
        // To pass any data to next activity
        intent.putExtra("keyIdentifier", "anyValue")
        // start your next activity
        startActivity(intent)
    }
    fun seeList(view : View){
        val intent = Intent(this, ListActivity::class.java)
        // To pass any data to next activity
        intent.putExtra("keyIdentifier", "anyValue")
        // start your next activity
        startActivity(intent)
    }

}
