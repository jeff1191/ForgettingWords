package com.forgettingwords.jeff.forgettingwords.service

import com.forgettingwords.jeff.forgettingwords.model.DataModel
import com.forgettingwords.jeff.forgettingwords.model.IrregularVerb
import java.io.File

class IrregularVerbsService(var pathFile: String): IService {

    override fun onCreate(): List<DataModel> {
        var list = File(pathFile).bufferedReader().readLines().map { line ->
            var data = line.split(",")

            IrregularVerb(data[0], data[1], data[2])
        }

        return list
    }
}