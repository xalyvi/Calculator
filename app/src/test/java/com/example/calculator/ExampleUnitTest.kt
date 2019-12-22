package com.example.calculator

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.lang.UnsupportedOperationException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    lateinit var calculator: Calculator

    @Before
    fun setUp() {
        calculator = Calculator()
    }

    @Test
    fun plus() {
        var string = "5.5654+7"

        string = calculator.calculate(string)

        assertEquals("12.5654", string)
    }

    @Test
    fun minus() {
        var string = "5.5654-7"

        string = calculator.calculate(string)

        assertEquals("-1.4346", string)
    }

    @Test
    fun divide() {
        var string = "10.2/2"

        string = calculator.calculate(string)

        assertEquals("5.1", string)
    }

    @Test
    fun times() {
        var string = "53.645*2.4"

        string = calculator.calculate(string)

        assertEquals("128.748", string)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun divideByZero() {
        calculator.calculate("53.645/0")
    }

    @Test
    fun evaluate() {
        var string = "2+2*2/2"

        string = calculator.calculate(string)

        assertEquals("4", string)
    }


    @Test(expected = UnsupportedOperationException::class)
    fun evaluateWithDivideByZero() {
        calculator.calculate("2+2*2/0")
    }
}
