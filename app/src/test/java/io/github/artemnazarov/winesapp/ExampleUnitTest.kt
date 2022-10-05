package io.github.artemnazarov.winesapp

import co.infinum.retromock.Retromock
import io.github.artemnazarov.winesapp.data.RetrofitInstance
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
        val retromock = Retromock.Builder()
            .retrofit(RetrofitInstance.instance)
            .build()
    }
}