#!/usr/bin/env kotlin

//4.5.5枚举类
//在Kotlin中，枚举类（enum class）用于定义一组有限的常量集合，通常用于表示状态、类型或选项。
//枚举类不仅可以定义枚举常量，还可以包含属性和方法。属性可以在枚举类中声明属性，并且每个枚举常量都必须初始化这些属性。方法可以在枚举类中定义普通方法，也可以为每个枚举常量单独实现不同的行为（通过匿名类方式）。枚举类可以包含构造函数，用于初始化枚举常量的属性。每个枚举常量可以覆盖枚举类的方法，实现不同的行为。
//这些特性使得 Kotlin 的枚举类比传统的枚举更加灵活，适用于需要附加数据或行为的枚举类型。
//（1）基本枚举类

//假设有一个Color枚举，表示颜色：
enum class Color {
    RED, GREEN, BLUE
}

fun main(){
    val color: Color = Color.RED
    println(color) // 输出: RED
}
//使用enum class关键字定义枚举类Color，其中RED、GREEN和BLUE是Color 枚举的实例，它们默认是Color类型的常量。


//（2）枚举类的属性和方法

enum class Color(val rgb: Int) {
    RED(0xFF0000), 
    GREEN(0x00FF00), 
    BLUE(0x0000FF);

    fun printColorCode() {
        println("Color code: #${rgb.toString(16).uppercase()}")
    }
}

fun main(){
    val color = Color.GREEN
    color.printColorCode() // 输出: Color code: #00FF00
}
//第1行代码Color枚举类定义了构造参数rgb，用于存储颜色的十六进制值。在第2行到第4行代码中，每种颜色在初始化的时候，都有一个Int类型的参数。
//第6行代码printColorCode()方法，用于打印颜色的十六进制代码。
//第7行代码rgb.toString(16).uppercase()是将整数转换为16进制的字符串格式。


//（3）枚举类中的匿名类

//枚举类的每个枚举实例可以单独实现方法。
enum class Shape {
    CIRCLE {
        override fun area(): Double = Math.PI * 2 * 2
    },
    RECTANGLE {
        override fun area(): Double = 3.0 * 4.0
    };

    abstract fun area(): Double
}

fun main(){
    println(Shape.CIRCLE.area())    // 输出: 12.566370614359172
    println(Shape.RECTANGLE.area()) // 输出: 12.0
}
//Shape枚举类定义了area()抽象方法。CIRCLE和RECTANGLE分别提供自己的 area()实现。枚举类可以像普通类一样定义抽象方法，每个实例可以提供不同的实现。


//（4）使用 when 处理枚举
//when语句可以用来处理枚举类型的值:
val color = Color.RED
val colorInfo = when (color) {
        Color.RED -> "This is Red"
        Color.GREEN -> "This is Green"
        Color.BLUE -> "This is Blue"
    }
}

fun main(){
    println(colorInfo) // 输出: This is Red
}
//when语句可以匹配枚举实例，从而提供对应的描述。在 when 语句中处理所有可能的枚举值，可以避免else语句，让代码更安全。


//（5）获取所有枚举值
//Kotlin提供了values()和valueOf()方法，分别用于获取所有枚举值和从字符串解析枚举值。
fun main(){
    // 获取所有枚举值
    val colors = Color.values()
    println(colors.joinToString()) // 输出: RED, GREEN, BLUE

    // 从字符串获取枚举值
    val redColor = Color.valueOf("RED")
    println(redColor) // 输出: RED
}
//第3行代码Color.values()返回所有的枚举值数组，第4行代码的joinToString()，集合（List、Set、Array 等）的一个扩展函数，用于将集合的元素连接成字符串，并允许自定义分隔符、前缀、后缀等格式函数所有枚举值组合在一起。默认情况下，使用的分隔符是逗号，如果自定义分隔符，可以尝试代码.joinToString(separator = " - ")。
//第7行代码Color.valueOf("RED")是根据字符串名称获取对应的枚举值。