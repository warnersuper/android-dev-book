#!/usr/bin/env kotlin

//定义函数

//（1）break 语句：终止整个循环
//函数是程序的基本组成部分，用于封装可重复执行的代码逻辑。Kotlin 提供了一种简洁而强大的函数定义方式，支持参数、返回值、默认参数、可变参数、Lambda 表达式等特性，使代码更加清晰和灵活
//基本语法：
fun 函数名(参数1: 类型, 参数2: 类型): 返回类型 {
        // 函数体
        return 返回值
    }


//Kotlin使用fun关键字来定义函数，参数列表可以有多个参数。如果函数没有返回值，返回类型可以省略，默认为 Unit（类似void）。
//示例 ：基本函数
fun greet(name: String): String {
        return "Hello, $name!"
    }

println(greet("Warner"))  // 输出：Hello, Warner!


//Kotlin 严格要求参数类型，不能像 Python 那样省略类型声明。返回值类型必须声明，除非是 Unit 类型（可以省略）。
//示例 ：返回 Unit（可省略）
fun printMessage(message: String) {
        println(message)
    }

printMessage("Kotlin is fun!") // 输出：Kotlin is fun!

//函数printMessage没有定义返回值，则默认的返回值为Unit类型，等同于下面的代码：
fun printMessage(message: String): Unit {
        println(message)
    }


//示例 ：带参数和返回值的函数
fun add(a: Int, b: Int): Int {
        return a + b
    }
println(add(3, 5)) // 输出：8
///add(a: Int, b: Int): Int 定义了 两个 Int 参数，返回 Int 类型。







