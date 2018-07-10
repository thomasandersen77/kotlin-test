package org.andtho.kotlin.web.restkotlin.functional

import org.junit.Test
import kotlin.test.assertTrue

class HighOrderFunctions {

    @Test
    fun test_is_even_numbers() {
        val isEven : (Int) -> Boolean = { it % 2 == 0 }
        val listOfInts = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val filterList = listOfInts.filter(isEven)
        filterList.forEach({ it ->
            assertTrue { it % 2 == 0 }
            println(it)
        })
    }
}