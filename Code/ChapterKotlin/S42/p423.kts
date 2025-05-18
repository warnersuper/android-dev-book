#!/usr/bin/env kotlin

//自动推断数据类型示例
val number = 42       // 推断为 Int
val pi = 3.1415        // 推断为 Double
val isHappy = true     // 推断为 Boolean

//相当于：
val number: Int = 42
val pi: Double = 3.1415
val isHappy: Boolean = true

//显式声明与类型推断对比示例：
// 显式声明类型
val explicitInt: Int = 10
val explicitDouble: Double = 5.5

// 省略类型声明，Kotlin 自动推断
val inferredInt = 10       // 推断为 Int
val inferredDouble = 5.5   // 推断为 Double

//如果变量的类型不确定，可以使用Any类型，这是Kotlin中的顶级类型
var anything: Any = "Kotlin"     // 允许存储任意类型
anything = 42                 // 也可以存储 Int