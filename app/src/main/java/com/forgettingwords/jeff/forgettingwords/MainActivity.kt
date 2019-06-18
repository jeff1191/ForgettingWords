package com.forgettingwords.jeff.forgettingwords

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.support.design.widget.FloatingActionButton
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.forgettingwords.jeff.forgettingwords.db.DatabaseHelper
import com.forgettingwords.jeff.forgettingwords.model.UrbanWord
import com.forgettingwords.jeff.forgettingwords.service.ServiceManager
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


    fun showUrbanWords(view : View) {
//        val intent = Intent(this, UrbanActivity::class.java)
        val urbanButton = findViewById(R.id.urbanWordsId) as Button
        var urbanList: List<UrbanWord> = emptyList()
        val mAlertDialog = AlertDialog.Builder(this@MainActivity)
        var dialogActive = false

        UrbanActivity.doAsync {
            urbanList = ServiceManager.getServiceList(ServiceManager.URBAN_VERB_INS) as List<UrbanWord> // todo this sucks


            this@MainActivity.runOnUiThread(Runnable {
                mAlertDialog.setTitle(urbanList[0].name) //set alertdialog title
                mAlertDialog.setMessage(urbanList[0].meaning + "\n" + "\n" + urbanList[0].example) //set alertdialog message
            })
        }.execute()


        urbanButton.setOnClickListener {

            mAlertDialog.setIcon(R.mipmap.ic_launcher_round) //set alertdialog icon
            mAlertDialog.setPositiveButton("Next") { dialog, id ->
                //perform some tasks here
                dialogActive = true
                dialog.dismiss()
                mAlertDialog.setTitle(urbanList[2].name) //set alertdialog title
                mAlertDialog.setMessage(urbanList[2].meaning + "\n" + "\n" + urbanList[1].example)
            }

            mAlertDialog.setNegativeButton("Previous") { dialog, id ->
                //perform som tasks here
                dialogActive = true

                mAlertDialog.setTitle(urbanList[1].name) //set alertdialog title
                mAlertDialog.setMessage(urbanList[1].meaning + "\n" + "\n" + urbanList[1].example)
            }
            mAlertDialog.setCancelable(false)


            if(!dialogActive)
                mAlertDialog.show()
            WordStateSt.init(dbHelper.getPlayWords())
//        startActivity(intent)
        }
    }
    fun showManager(view : View){
        val intent = Intent(this, ManagerActivity::class.java)
        WordStateSt.init(dbHelper.getPlayWords())
        startActivity(intent)
    }

}
class doAsync(val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
    override fun doInBackground(vararg params: Void?): Void? {
        handler()
        return null
    }
}
