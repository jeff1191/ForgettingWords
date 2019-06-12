package com.forgettingwords.jeff.forgettingwords.service

import com.forgettingwords.jeff.forgettingwords.model.DataModel
import com.forgettingwords.jeff.forgettingwords.model.PhrasalVerb
import java.io.File

class PhrasalVerbService(var pathFile: String, var type: String): IService {

    override fun onCreate(): List<DataModel> {
        var category =""


        var list = File(pathFile).bufferedReader().readLines().map { line ->
            var data = line.split(":")


            if(type.equals("startWith"))
                PhrasalVerb(data[0], data[1], data[0].split(" ")[0][0].toString())
            else
                if(type.equals("firstWord"))
                    PhrasalVerb(data[0], data[1], data[0].split(" ")[0])
                else{
                        if(data.size == 1){
                            category = line.replace("###", "")
                            PhrasalVerb("", "", "")
                        }
                        else
                    PhrasalVerb(data[0], data[1], category)
                }


        }.filter { x -> x.name.isNotEmpty() &&  x.meaning.isNotEmpty() && x.category.isNotEmpty()}


        return list
    }

}