#!/usr/bin/env kotlin

//基本语法:
if (条件) {
        // 执行代码
    } else if (条件) {
        // 执行代码
    } else {
        // 执行代码
    }

//示例 ：基本 if-else
val age = 18

if (age >= 18) {
        println("成年人")
    } else {
        println("未成年人")
    }

//示例 ：if 作为表达式
val a = 10
val b = 20
val max = if (a > b) a else b   // `if` 直接返回值
println(max) // 输出 20

//Kotlin中的if-else语句可以返回值，最后一个表达式的值作为 if 语句的返回值，例如：
val max = if (a > b) {
        a  // 直接返回 a
    } else {
        b  // 直接返回 b
}
