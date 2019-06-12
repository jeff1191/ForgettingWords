package com.forgettingwords.jeff.forgettingwords

import com.forgettingwords.jeff.forgettingwords.model.DataModel
import com.forgettingwords.jeff.forgettingwords.service.IrregularVerbsService
import com.forgettingwords.jeff.forgettingwords.service.PhrasalVerbService
import com.forgettingwords.jeff.forgettingwords.service.ServiceManager
import com.forgettingwords.jeff.forgettingwords.service.UrbanService
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun testPhrasalVerbService() {
        printList(  ServiceManager.getServiceList(ServiceManager.PHRASAL_VERB_INS))

    }
    @Test
    fun testUrbanService() {
        printList(  ServiceManager.getServiceList(ServiceManager.URBAN_VERB_INS))

    }
    @Test
    fun testIrregularService() {
        printList(  ServiceManager.getServiceList(ServiceManager.IRREGULAR_VERB_INS))
    }

    fun printList(dataList: List<DataModel>){
        dataList.forEach { x -> println(x) }
    }
}
