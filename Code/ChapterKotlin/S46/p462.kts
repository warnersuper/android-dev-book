#!/usr/bin/env kotlin

//4.6.2 Lambda表达式
//Lambda表达式（匿名函数）是一种简洁的函数表示方法，它可以作为参数传递给函数或赋值给变量，避免定义额外的函数，提高代码的可读性和灵活性。 
//在 Kotlin 中，Lambda表达式的基本语法如下：
{ 参数列表 -> 函数体 }
//说明如下：
//参数列表：表示传入的参数，可以有多个，也可以没有
//->：Lambda的分隔符，左边是参数，右边是函数体
//函数体：表达式或代码块，返回值由最后一行决定

//示例：使用Lambda计算两个数的和
fun main() {
    // 定义一个 Lambda 表达式，计算两个数的和
    val sum: (Int, Int) -> Int = { a, b -> a + b }
    println(sum(3, 5))  // 输出: 8
}
//第3行代码定义一个Lambda表达式，接收两个Int参数，返回它们的和。第4行代码sum(3, 5)调用 Lambda，就像普通函数一样。

//示例：手动遍历列表并筛选偶数
fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6)

    // 使用 Lambda 手动筛选偶数
    val evenNumbers = mutableListOf<Int>()
    val isEven: (Int) -> Boolean = { num -> num % 2 == 0  }  // Lambda 表达式判断是否为偶数

    for (num in numbers) {
        if (isEven(num)) {  // 调用 Lambda 判断
            evenNumbers.add(num)
        }
    }

    println(evenNumbers)  // 输出: [2, 4, 6]
}
//第6行代码定义一个Lambda表达式，用于判断数字是否为偶数，形式是(Int) -> Boolean，输入参数是整数，输出是布尔。

//Kotlin能自动推断Lambda参数的类型，可以省略显式声明。
val multiply = { x: Int, y: Int -> x * y }  // 带类型
val divide: (Int, Int) -> Int = { x, y -> x / y }  // 省略类型

fun main() {
    println(multiply(4, 5))  // 输出: 20
    println(divide(10, 2))  // 输出: 5
}
//第1行代码multiply直接定义Lambda并显式声明参数类型。第2行代码divide通过类型推断省略参数类型。
//如果 Lambda 只有一个参数，可以使用 it 代替显式参数名称。
val square: (Int) -> Int = { it * it }

fun main() {
    println(square(6))  // 输出: 36
}
//第1行代码的Lambda中只有一个参数，因此使用it代表传入的唯一参数，计算平方值。
