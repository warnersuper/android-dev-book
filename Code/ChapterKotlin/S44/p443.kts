#!/usr/bin/env kotlin

//4.4.3 可变参数

//可变参数（Varargs）允许函数接收不定数量的参数，调用时可以传入多个值，而函数内部会将它们视为数组进行处理。这对于需要处理多个同类型参数的场景（例如累加、拼接等）非常实用
fun sumAll(vararg numbers: Int): Int {
        return numbers.sum()
    }
println(sumAll(1, 2, 3, 4, 5)) // 输出：15
//vararg numbers: Int 允许传入多个参数，并在函数内部作为数组处理









