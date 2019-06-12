package com.forgettingwords.jeff.forgettingwords

import com.forgettingwords.jeff.forgettingwords.service.PhrasalVerbService
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
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testPhrasalVerbService() {
       PhrasalVerbService("/home/jeff/StudioProjects/ForgettingWords/app/sampledata/firstWord.txt").onCreate("firstWord")
       PhrasalVerbService("/home/jeff/StudioProjects/ForgettingWords/app/sampledata/startWith.txt").onCreate("startWith")
       PhrasalVerbService("/home/jeff/StudioProjects/ForgettingWords/app/sampledata/individualCategories.txt").onCreate("individualCategories")
    }
    @Test
    fun testUrbanService() {
       UrbanService("https://www.urbandictionary.com/random.php").onCreate()
    }
}
