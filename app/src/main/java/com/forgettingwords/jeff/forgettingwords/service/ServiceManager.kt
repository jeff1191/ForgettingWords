package com.forgettingwords.jeff.forgettingwords.service

import com.forgettingwords.jeff.forgettingwords.model.DataModel

object ServiceManager {
    var IRREGULAR_VERB_INS: Int = 0
    var PHRASAL_VERB_INS: Int = 1
    var URBAN_VERB_INS: Int = 2

    var startWithPathFile: String = "sampledata/startWith.txt"
    var individualPathFile: String = "sampledata/individualCategories.txt"
    var firstWordPathFile: String = "sampledata/firstWord.txt"
    //------------------------------------
    var irregularPathFile: String = "sampledata/irregularVerbs.txt"
    var urbanUrl: String = "https://www.urbandictionary.com/random.php"


    fun getServiceList(ins: Int): List<DataModel>{
       var ret = when (ins) {
            IRREGULAR_VERB_INS -> IrregularVerbsService(irregularPathFile).onCreate()
            URBAN_VERB_INS -> UrbanService(urbanUrl).onCreate()
            PHRASAL_VERB_INS -> PhrasalVerbService(startWithPathFile, "startWith").onCreate()
                    .plus(PhrasalVerbService(firstWordPathFile, "firstWord").onCreate())
                    .plus(PhrasalVerbService(individualPathFile, "individualCategories").onCreate())
           else ->
               throw Exception("Hi There!")
        }

        return ret
    }
}