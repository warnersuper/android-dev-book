#!/usr/bin/env kotlin

//整数类型示例：
val intValue: Int = 42
val longValue: Long = 123456789L
val shortValue: Short = 300
val byteValue: Byte = 127

//浮点类型示例：
val pi: Double = 3.1415926535
val e: Float = 2.71828F

//布尔类型示例：
val isKotlinAwesome: Boolean = true

//字符类型示例：
val letter: Char = 'K'

//字符串类型示例：
val greeting: String = "Hello, Kotlin!"
println(greeting.length)  // 获取字符串长度

//需要注意的是，Kotlin 的数值类型不会自动转换，需要显式转换。例如Int不能隐式转换为 Long，需要使用toLong()函数进行转换
val myInt: Int = 300
val x: Long = myInt.toLong()