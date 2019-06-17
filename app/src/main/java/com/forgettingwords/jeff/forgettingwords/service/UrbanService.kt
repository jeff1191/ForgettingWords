package com.forgettingwords.jeff.forgettingwords.service

import com.forgettingwords.jeff.forgettingwords.model.DataModel
import com.forgettingwords.jeff.forgettingwords.model.UrbanWord
import org.jsoup.Jsoup

class UrbanService (var url: String): IService{
    var validNames: List<String> = object : ArrayList<String>() {
        init {
            add("def-header")
            add("meaning")
            add("example")
        }
    }
    val doc = Jsoup.connect(url).get()


    override fun onCreate(): List<DataModel> {
        val list : MutableList<UrbanWord> = ArrayList()
        val info = doc.select("div#content")

        for (child in info[0].childNodes()) {
            if (child.attr("class").equals("def-panel")) {
                val ret = UrbanWord()
                for (contentChild in child.childNodes()) {
                    val classDivName = contentChild.attributes().get("class")

                    if (validNames.contains(classDivName)) {

                        var toPrint = Jsoup.parse(contentChild.outerHtml()).text()

                        if (toPrint.split(" ").size > 1 && classDivName.equals("def-header", ignoreCase = true))
                            toPrint = toPrint.replace("unknown", "")


                        if (classDivName.equals("def-header", ignoreCase = true))
                            ret.name = toPrint


                        if (classDivName.equals("meaning", ignoreCase = true))
                            ret.meaning = toPrint


                        if (classDivName.equals("example", ignoreCase = true))
                            ret.example = "E.g: $toPrint"

                    }

                }
                list.add(ret)
            }

        }

        return list
    }



}