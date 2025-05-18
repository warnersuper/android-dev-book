#!/usr/bin/env kotlin

//for循环用于遍历范围（range）、数组（array）、集合（list/set）等数据结构，让代码更加简洁
//基本语法:
for (变量 in 迭代对象) {
        // 循环体
}

//示例 ：遍历区间
for (i in 1..5) {
        println(i)
    }
// 输出：1 2 3 4 5

//示例 ：倒序遍历
for (i in 5 downTo 1) {
        println(i)
    }
// 输出：5 4 3 2 1

//示例 ：使用 step
for (i in 1..10 step 2) {
        println(i)
    }
// 输出：1 3 5 7 9

//示例 ：遍历数组
val fruits = listOf("Apple", "Banana", "Cherry")
for (fruit in fruits) {
        println(fruit)
    }
// 输出：Apple Banana Cherry

//示例 ：索引遍历
val names = listOf("Alice", "Bob", "Charlie")
for ((index, name) in names.withIndex()) {
        println("索引 $index -> 值 $name")
    }
// 输出：
// 索引 0 -> 值 Alice
// 索引 1 -> 值 Bob
// 索引 2 -> 值 Charlie