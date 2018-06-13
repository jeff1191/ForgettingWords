package com.forgettingwords.jeff.forgettingwords

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView


class ListActivity : Activity() {
//    var lista: ArrayList<String>? = null
    var adaptor: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val lv = findViewById(R.id.sampleListView) as ListView
        lv.adapter = ListExampleAdapter(this)
    }

    private class ListExampleAdapter(context: Context) : BaseAdapter() {
        internal var sList = arrayOf("One", "Two", "Three", "Four", "Five", "Six", "Seven",
                "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen")
        private val mInflator: LayoutInflater

        init {
            this.mInflator = LayoutInflater.from(context)
        }

        override fun getCount(): Int {
            return sList.size
        }

        override fun getItem(position: Int): Any {
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

            vh.label.text = sList[position]
            return view
        }
    }

    private class ListRowHolder(row: View?) {
        public val label: TextView

        init {
            this.label = row?.findViewById(R.id.label) as TextView
        }
    }
}