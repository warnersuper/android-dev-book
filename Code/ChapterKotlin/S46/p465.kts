#!/usr/bin/env kotlin

//4.6.5高阶函数
//高阶函数是指将函数作为参数或返回值的函数。这是函数式编程的重要特性之一，极大地提升了代码的灵活性和可重用性。
//高阶函数的定义：如果一个函数接收另一个函数作为参数，或者返回一个函数作为结果，那么这个函数就是“高阶函数”。

//（1）高阶函数的基本语法

fun <函数名>(参数名: (参数类型) -> 返回类型): 返回类型

// 	示例：定义并调用一个高阶函数
fun operate(a: Int, b: Int, op: (Int, Int) -> Int): Int {
    return op(a, b)
}
fun add(a:Int, b:Int): Int{
    return a+b
}
fun sub(a:Int, b:Int): Int{
    return a-b
}
fun main() {
    val result1 = operate(3, 5, ::add)
    val result2 = operate(3, 5, ::sub)
    println(result1)  // 输出: 8
    println(result2)  // 输出: -2
}
//第1行代码定义了一个高阶函数，接收两个整数参数a和b，第三个参数op是一个函数类型：(Int, Int) -> Int，表示它接受两个Int，返回一个Int。
//第4行和7行代码定义了一个普通函数add()和sub()，实现两个整数相加和相减。第8行代码中的::add是函数引用语法，表示将函数add作为参数传入。
//上面的代码写了一次operate，但它可以被任意的加法、乘法、减法等操作“定制”，实现逻辑的抽象与复用。
//使用Lambda将进一步简化高阶函数，下面的代码实现了上方代码相同的功能：
fun operate(a: Int, b: Int, op: (Int, Int) -> Int): Int {
    return op(a, b)
}
fun main() {
    val result1 = operate(3, 5) { x, y -> x + y }
    val result2 = operate(3, 5) { x, y -> x - y }
    println(result1)  // 输出: 8
    println(result2)  // 输出: -2
}
//在第5行代码中，operate(3, 5){ x, y -> x + y }传入 Lambda 表达式，实现加法功能，原始形式为operate(3, 5, { x, y -> x + y })，这两个写法是等价的，只是语法形式不同。当Lambda作为函数的最后一个参数的时候，可以把Lambda放在小括号的外部，这种Lambda外置语法更加的简洁。


//（2）函数类型的声明方式

//函数类型可简写为：
(Int, Int) -> Int

//可作为变量类型使用：
val multiply: (Int, Int) -> Int = { x, y -> x * y }
//val multiply声明一个不可变变量multiply，(Int, Int) -> Int指定变量的类型接受两个Int参数，返回一个Int的函数类型，Lambda表达式表示将两个参数相乘的函数逻辑。
//Lambda表达式并赋值给变量multiply，可以像调用普通函数一样使用它，完成两个整数相乘的操作。简洁且强大，是Kotlin函数式编程风格的体现。
//相当于写成普通函数的形式：
fun multiply(x: Int, y: Int): Int {
    return x * y
}

//用于高阶函数参数：
fun applyOp(a: Int, b: Int, op: (Int, Int) -> Int) = op(a, b)
//fun applyOp(...)定义一个函数，名字叫 applyOp；接收两个整数参数a和b，第3个参数是一个函数类型：接收两个Int，返回一个Int；op(a, b)是函数体：将参数a和b传给op，并返回其结果。
//使用示例：
val sum = applyOp(3, 5) { x, y -> x + y }
println(sum)  // 输出：8

val product = applyOp(4, 6) { x, y -> x * y }
println(product)  // 输出：24


//（3）函数作为返回值
//函数也可以返回另一个函数。
fun createOperator(op: String): (Int, Int) -> Int {
    return when (op) {
        "add" -> { a, b -> a + b }
        "sub" -> { a, b -> a - b }
        else -> { _, _ -> 0 }
    }
}

fun main() {
    val add = createOperator("add")
    println(add(4, 6))  // 输出: 10
}

//createOperator返回值是一个函数，形式是(Int, Int) -> Int。因此函数的返回值也是一个函数。


//（4）使用内联函数提升性能
//使用内联函数（inline function）可以提升性能，尤其是当函数接收Lambda表达式作为参数时。其原理是：内联函数在编译时会将函数体和传入的Lambda代码直接“插入”到调用处，避免了运行时产生函数对象和函数调用开销。
//为什么内联函数会提升性能？当把Lambda传给一个普通函数时，Kotlin会创建一个匿名类或函数对象，然后分配内存和调用函数，这对性能是有一定损耗的，尤其是在大量调用或循环中。而使用inline关键字修饰函数后，编译器会将函数及其中的Lambda代码直接替换到调用位置，避免了函数对象的生成和调用栈的压入与弹出，减少了内存分配和垃圾回收压力。
inline fun doTwice(action: () -> Unit) {
    action()
    action()
}

fun main() {
    doTwice { println("执行") }  
    // 输出:
    //       执行
    //       执行
}
//使用inline编译器会将函数体复制到调用处，编译器内联后的等效代码如下，做了适当的简化：
fun main() {
    // 等效于将 doTwice 函数体替换到调用处
    println("执行")
    println("执行")
}
//Lambda{ println("执行") }被重复插入两次，完全消除了函数调用的开销，所以运行时不会产生任何函数对象或额外栈帧，性能更优。
