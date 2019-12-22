package com.example.calculator

import java.lang.UnsupportedOperationException
import java.util.*

class Calculator {

    fun calculate(string: String): String {

        val values: Stack<Double> = Stack()

        val ops: Stack<Char> = Stack()

        val tokens: CharArray = string.toCharArray()

        var i = 0
        while (i < tokens.size) {
            if (tokens[i] in '0'..'9' || tokens[i] == '.' ) {
                val stringBuffer = StringBuffer()
                while (i < tokens.size && (tokens[i] in '0'..'9' || tokens[i] == '.')) {
                    stringBuffer.append(tokens[i++])
                }
                values.push(stringBuffer.toString().toDouble())
            } else if (tokens[i] == '+' || tokens[i] == '-' ||
                    tokens[i] == '/' || tokens[i] == '*') {
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())) {
                    values.push(evalOp(values.pop(), values.pop(), ops.pop()))
                }
                ops.push(tokens[i])
                i++
            }
        }

        while (!ops.empty()) {
            values.push(evalOp(values.pop(), values.pop(), ops.pop()))
        }
        var result: Double = values.pop()

        if (result - result.toInt() == 0.0) {
            return result.toInt().toString()
        }

        return (Math.round(result * 10000.0) / 10000.0).toString()

    }

    private fun evalOp(second: Double, first: Double, oper: Char): Double {
        when(oper) {
            '+' -> return first.plus(second)
            '-' -> return first.minus(second)
            '*' -> return first.times(second)
            '/' -> {
                if (second.equals(0.00))
                    throw UnsupportedOperationException("Cannot divide by zero")
                return first.div(second)
            }
        }
        return 0.00
    }

    private fun hasPrecedence(first: Char, second: Char): Boolean {
        return !((first == '*' || first == '/') && (second == '-' || second == '+'))
    }
}