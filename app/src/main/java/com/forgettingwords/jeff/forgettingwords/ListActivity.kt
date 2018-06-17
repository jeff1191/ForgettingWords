package com.forgettingwords.jeff.forgettingwords

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.forgettingwords.jeff.forgettingwords.db.DatabaseHelper
import com.forgettingwords.jeff.forgettingwords.model.WordMeaning
import android.R.string.cancel
import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.text.style.UnderlineSpan
import android.text.SpannableString
import android.text.style.BackgroundColorSpan


class ListActivity : Activity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        //init db
        dbHelper = DatabaseHelper(this)

        val lv = findViewById<ListView>(R.id.sampleListView)
        lv.adapter = ListExampleAdapter(this,dbHelper)
    }

    private class ListExampleAdapter(context: Context, dbHelperActivity: DatabaseHelper) : BaseAdapter() {

        internal var dbHelper = dbHelperActivity
        internal var sList : List<WordMeaning> = dbHelper.getAll()

        private val mInflator: LayoutInflater

        init {
            this.mInflator = LayoutInflater.from(context)

        }

        override fun getCount(): Int {

            return sList.size
        }

        override fun getItem(position: Int): WordMeaning {
            return sList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            val view: View?
            val vh: ListRowHolder
            if (convertView == null) {
                view = this.mInflator.inflate(R.layout.list_row, parent, false)
                vh = ListRowHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ListRowHolder
            }

            vh.name.text = sList[position].name + "("+ "%.2f".format(sList[position].percentage)+"%)"
            vh.meaning.text = sList[position].meaning



            when (sList[position].percentage.toInt()) {
                in 0..40  ->{
                    val str = SpannableString(vh.name.text)
                    str.setSpan(BackgroundColorSpan(Color.YELLOW), 0, vh.name.text.length, 0)
                    vh.name.setText(str)
                }
                in 90..100  ->{

                    vh.name.setTextColor(Color.GREEN)
                }

            }


            vh.image.setOnClickListener { view ->

                val alertDialog = AlertDialog.Builder(view.getContext() as Activity)
                alertDialog.setMessage("Are you sure you want to delete -> ${sList[position].name}?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                            dbHelper.deleteById(sList[position].id!!)
                            sList = dbHelper.getAll()
                            notifyDataSetChanged()
                        })
                        .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
                val alert = alertDialog.create()
                alert.show()
            }

            return view
        }
    }

    private class ListRowHolder(row: View?) {
        val name: TextView
        val meaning: TextView
        val image: ImageView

        init {
            this.name = row?.findViewById(R.id.nameListRow) as TextView
            this.meaning = row?.findViewById(R.id.meaningListRow) as TextView
            this.image = row?.findViewById(R.id.deleteElemListRow) as ImageView
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelper.connectionSource.close()
    }
}