#!/usr/bin/env kotlin

//条件语句when
//基本语法:
when (变量) {
        值1 -> 代码块
        值2 -> 代码块
       else -> 代码块 // 可选
}

//示例 ：基本使用方法
val num = 2

when (num) {
        1 -> println("一")
        2 -> println("二")
        else -> println("其他")
    }

//示例 ：表达式
val day = 3
val dayName = when (day) {
        1 -> "星期一"
        2 -> "星期二"
        3 -> "星期三"
        4 -> "星期四"
        5 -> "星期五"
        6 -> "星期六"
        7 -> "星期天"
        else -> "无效日期"
    }
println(dayName) // 输出：星期三

//示例 ：范围匹配
val score = 85
val grade = when (score) {
        in 90..100 -> "优秀"
        in 70..89 -> "良好"
        in 60..69 -> "及格"
        else -> "不及格"
    }
println(grade) // 输出：良好

//示例 ：匹配多个值
val fruit = "Apple"

when (fruit) {
        "Apple", "Banana" -> println("这是水果")
        "Carrot" -> println("这是蔬菜")
        else -> println("未知类别")
    }


