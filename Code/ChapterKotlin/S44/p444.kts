#!/usr/bin/env kotlin

//4.4.4 单表达式函数

//单表达式函数（Single-Expression Function）是一种简洁的函数写法，适用于函数体只有单个表达式的情况。可以省略{}代码块和return关键字，使代码更加简洁和可读
//示例 ：单表达式函数
fun square(x: Int) = x * x
println(square(5)) // 输出：25


//上面的代码使用了单表达式函数，等价于下面的代码：
fun square(x: Int): Int {
        return x * x
    }


//示例 ：单表达式，返回 String
fun greet(name: String) = "Hello, $name!"
println(greet("Alice"))   // 输出 Hello, Alice


//这个单表达式函数返回值是字符串，其中"Hello, $name!" 是字符串模板的用法，它允许在字符串中直接嵌入变量或表达式。变量name的值"Alice"被替换到字符串中，因此输出的字符串是Hello, Alice。
//如果需要在字符串中插入更复杂的表达式，可以使用${}包裹表达式：
val age = 25
println("Next year, you will be ${age + 1} years old.")
// 输出：Next year, you will be 26 years old.


//示例 ：结合默认参数
fun add(a: Int, b: Int = 5) = a + b

println(add(10))   // 输出 15
println(add(10, 20))  // 输出 30


//这段代码定义了一个带默认参数的单表达式函数add，用于计算两个整数的和。a是必传参数，b是带默认值的参数，如果调用时没有提供b，它会默认使用5。a + b 采用单表达式函数写法，直接返回计算结果







