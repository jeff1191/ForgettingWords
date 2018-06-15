package com.forgettingwords.jeff.forgettingwords.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "words")
data class WordMeaning(

    @DatabaseField(columnName = "id", generatedId = true)
    var id: Int? = 0,

    @DatabaseField(columnName = "name")
    var name: String = "",

    @DatabaseField(columnName = "meaning")
    var meaning: String = "",

    @DatabaseField(columnName = "right_answers")
    var rightAnswers: Int = 5,

    @DatabaseField(columnName = "error_answers")
    var errorAnswers: Int = 5,

    @DatabaseField(columnName = "percentage")
    var percentage: Double = (rightAnswers * 100.0) / (rightAnswers + errorAnswers)
)
