package com.forgettingwords.jeff.forgettingwords

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.widget.TextView
import com.forgettingwords.jeff.forgettingwords.db.DatabaseHelper
import com.forgettingwords.jeff.forgettingwords.model.UrbanWord
import com.forgettingwords.jeff.forgettingwords.service.ServiceManager
import android.view.WindowManager
import xyz.klinker.android.floating_tutorial.FloatingTutorialActivity
import xyz.klinker.android.floating_tutorial.TutorialPage


class UrbanActivity: FloatingTutorialActivity() {
    //https://blog.klinkerapps.com/floating-tutorial-activity/

    override fun getPages(): List<TutorialPage> {
        return listOf(
                object : TutorialPage(this@UrbanActivity) {
                    override fun initPage() {
                        setContentView(R.layout.activity_urban)
                    }
                })

    }

    private lateinit var dbHelper: DatabaseHelper
    private var urbanList: List<UrbanWord> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urban)
//        setWindowParams()
        //init db
        dbHelper = DatabaseHelper(this)
        doAsync {
            urbanList = ServiceManager.getServiceList(ServiceManager.URBAN_VERB_INS) as List<UrbanWord> // todo this sucks

        val urbanWord = findViewById<TextView>(R.id.urbanWord)
        val urbanDescription = findViewById<TextView>(R.id.urbanDescription)



            this@UrbanActivity.runOnUiThread(java.lang.Runnable {
                urbanWord.setText(urbanList[0].name)
                urbanDescription.setText(urbanList[0].meaning + "\n"+ "\n" + urbanList[0].example)
            })
        }.execute()

    }

    fun setWindowParams() {
        val wlp = window.attributes
        wlp.dimAmount = 0f
        wlp.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        window.attributes = wlp
    }

    class doAsync(val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            handler()
            return null
        }
    }
}