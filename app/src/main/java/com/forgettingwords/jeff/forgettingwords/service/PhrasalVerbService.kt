package com.forgettingwords.jeff.forgettingwords.service

import com.forgettingwords.jeff.forgettingwords.model.PhrasalVerb
import java.io.File

class PhrasalVerbService(var pathFile: String) {

    fun onCreate(type: String) {
        var category =""


        var list = File(pathFile).bufferedReader().readLines().map { line ->
            var data = line.split(":")


            if(type.equals("startWith"))
                PhrasalVerb(data[0], data[1], data[0].split(" ")[0][0].toString())
            else
                if(type.equals("firstWord"))
                    PhrasalVerb(data[0], data[1], data[0].split(" ")[0][0].toString())
                else{
                        if(data.size == 1){
                            category = line.replace("###", "")
                            PhrasalVerb("", "", "")
                        }
                        else
                    PhrasalVerb(data[0], data[1], category)
                }


        }.filter { x -> x.name.isNotEmpty() &&  x.meaning.isNotEmpty() && x.category.isNotEmpty()}


        list.forEach { x ->

            println(x)
        }

    }



}