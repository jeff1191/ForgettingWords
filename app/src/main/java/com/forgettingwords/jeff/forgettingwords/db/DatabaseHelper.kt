package com.forgettingwords.jeff.forgettingwords.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.forgettingwords.jeff.forgettingwords.App
import com.forgettingwords.jeff.forgettingwords.db.DatabaseHelper.Companion.DBNAME
import com.forgettingwords.jeff.forgettingwords.db.DatabaseHelper.Companion.DBVERSION
import com.forgettingwords.jeff.forgettingwords.model.WordMeaning
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils


class DatabaseHelper : OrmLiteSqliteOpenHelper {

    constructor(context: Context): super(context, DBNAME, null, DBVERSION)

    companion object {
        val DBNAME = "words.sqlite"
        val DBVERSION = 1
    }

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTableIfNotExists(connectionSource, WordMeaning::class.java)
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?, oldVersion: Int, newVersion: Int) {
        TableUtils.dropTable<WordMeaning, Any>(connectionSource, WordMeaning::class.java, true)
        onCreate(database, connectionSource)
    }

    fun createOrUpdate(obj: Any): Dao.CreateOrUpdateStatus {
        val dao = getDao(obj.javaClass) as Dao<Any, *>
        return dao.createOrUpdate(obj)
    }

    fun getAll(clazz: Class<*>): List<*> {
        val dao = getDao(clazz)
        return dao.queryForAll()
    }

    fun getStudentById(aId: Int): WordMeaning? {
        val studentDao = getRuntimeExceptionDao(WordMeaning::class.java)
        val queryBuilder = studentDao.queryBuilder()
        queryBuilder.where().eq("id", aId)
        val students = queryBuilder.query()
        return if (students.isEmpty()) null else students[0]
    }

    fun deleteById(aId: Int): Int {
        val studentDao = getRuntimeExceptionDao(WordMeaning::class.java)
        val deleteBuilder = studentDao.deleteBuilder()
        deleteBuilder.where().eq("id", aId)
        return deleteBuilder.delete()
    }

}