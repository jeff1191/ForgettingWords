package com.forgettingwords.jeff.forgettingwords.dao

import com.forgettingwords.jeff.forgettingwords.db.DatabaseHelper
import com.forgettingwords.jeff.forgettingwords.model.WordMeaning
import com.j256.ormlite.dao.Dao

class WordDao {

    companion object {
        lateinit var dao: Dao<WordMeaning, Int>
    }


    fun add(table: WordMeaning) = dao.createOrUpdate(table)

    fun update(table: WordMeaning) = dao.update(table)

    fun delete(table: WordMeaning) = dao.delete(table)

    fun queryForAll() = dao.queryForAll()

    fun removeAll() {
        for (table in queryForAll()) {
            dao.delete(table)
        }
    }

}