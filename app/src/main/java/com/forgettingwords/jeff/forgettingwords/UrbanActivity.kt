package com.forgettingwords.jeff.forgettingwords

import android.app.Activity
import android.app.AlertDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatAutoCompleteTextView
import android.view.View
import android.widget.TextView
import com.forgettingwords.jeff.forgettingwords.db.DatabaseHelper
import com.forgettingwords.jeff.forgettingwords.model.UrbanWord
import com.forgettingwords.jeff.forgettingwords.service.ServiceManager
import android.view.WindowManager
import android.widget.Toast
import xyz.klinker.android.floating_tutorial.FloatingTutorialActivity
import xyz.klinker.android.floating_tutorial.TutorialPage


class UrbanActivity: AppCompatActivity() {
    //https://blog.klinkerapps.com/floating-tutorial-activity/
//
//    override fun getPages() = listOf<TutorialPage>(
//            object : TutorialPage(this@UrbanActivity) {
//                override fun initPage() {
//                    setContentView(R.layout.activity_urban)
//                    setNextButtonText(R.string.ok)
//                }
//
//                override fun animateLayout() {
//                    AnimationHelper.quickViewReveal(findViewById<View>(R.id.bottom_text), 300)
//                }
//            })

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

//object AnimationHelper {
//    fun animateGroup(vararg views: View) {
//        var startTime = 300
//
//        for (v in views) {
//            quickViewReveal(v, startTime.toLong())
//            startTime += 75
//        }
//    }
//
//    fun quickViewReveal(view: View, delay: Long) {
//        view.translationX = -1f * DensityConverter.toDp(view.context, 16)
//        view.alpha = 0f
//        view.visibility = View.VISIBLE
//
//        view.animate()
//                .translationX(0f)
//                .alpha(1f)
//                .setStartDelay(delay)
//                .start()
//    }
//}