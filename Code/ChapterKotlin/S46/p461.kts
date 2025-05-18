#!/usr/bin/env kotlin

//4.6.1 空安全
//Kotlin 通过空安全机制（Null Safety）大幅减少了空指针异常（NullPointerException，NPE)，提高了代码的安全性和稳定性。Kotlin明确区分可空类型与非空类型，并提供了多个操作符来安全地处理空（null）值。

//（1）非空类型
//在 Kotlin 中，默认情况下所有类型都是非空类型，如果需要存储空类型，必须显式声明可空类型，否则会报错。
var name: String = "Kotlin"
// name = null // 错误，非空类型不能赋值 null
// name变量被声明为非空类型 (String)，因此它不可以为null。


//（2）可空类型
//如果变量可能为可空类型，则在类型后加 ?，表示可空类型。
var name: String? = "Kotlin"
name = null  // 可空类型允许赋值 null
//name变量是可空类型，但是在后续的使用中，需要编程处理null的情况，否则编译器会报错。
//表4.4 可空类型及其表示方式
//类型	描述	示例
String?	可为空的字符串类型	val name: String? = null
Int?	可为空的整数类型	val age: Int? = null
Double?	可为空的双精度浮点数类型	val price: Double? = null
Boolean?	可为空的布尔类型	val isActive: Boolean? = null
List<String>?	可为空的字符串列表类型	val items: List<String>? = null
Map<String, Int>?	可为空的键值对映射类型	val scores: Map<String, Int>? = null
Person?	可为空的自定义对象类型	val person: Person? = null


//（3）强制非空
//强制非空使用两个惊叹号（!!）告诉编译器“确信这个值不是 null”。如果不幸这个值是null，此时否则会抛出异常。
var name: String? = null
println(name!!.length) // ⚠️ 运行时报错：NullPointerException
//第2行代码name!!强制指定name为非空，而实际上此时name是null，这样代码会出现空指针异常。正确的做法是先判断变量name是否为null，如下：
var name: String? = "Kotlin"
if (name != null) {
    println(name!!.length)  // 6
}

//（4）安全调用操作符
//安全调用操作符 (?.)可避免访问null导致的异常。如果对象为null，则整个表达式返回 null，不会抛出异常。
var name: String? = null
println(name?.length)  // 输出: null，不会报错
//等价于：
if (name != null) {
    println(name.length)
} else {
    println(null)
}
//从上面的代码可以发现，安全调用操作符的使用，可以大量减少代码量，同时减少的产生异常的可能性。

data class Person(val name: String?, val address: String?)
val person: Person? = Person(null, "New York")

println(person?.name?.length)  // 输出: null
println(person?.address?.length)  // 输出: 8
//安全调用操作符允许安全地访问嵌套属性，这是一种链式调用，如果任何一级为null，整个链条返回null，不会导致空指针异常。


//（5）Elvis操作符
//Elvis 操作符（?:是用于处理可空类型的一个运算符，它的作用是当左侧表达式的结果为null时，返回右侧的默认值。它常用于提供null值的替代方案，避免空指针异常。
var name: String? = null
val length = name?.length ?: 0  // name 为 null，返回 0
println(length)  // 输出: 0
//第2行代码中，?: 0是Elvis 操作符。如果左侧表达式（name?.length）的结果为null，则返回右侧的0作为默认值；如果左侧表达式不为null，则返回左侧name的长度length。
//Elvis操作符可以和throw语句结合，在不符合预期时抛出异常。
val name: String? = null
val result = name ?: throw IllegalArgumentException("name 不能为空")
//在name为null时，抛出IllegalArgumentException


//（6）Let
//let是一个常用的作用域函数，主要用于对某个对象执行一段操作，尤其适合处理可空类型，避免出现空指针异常。

val name: String? = "Kotlin"

name?.let {
    println("名字是：$it")  // 输出: 名字是：Kotlin
}

//第3行代码中的?.let { ... }，表示如果name不为 null，就执行let代码块中的内容。第4行代码it是let中的默认参数名，代表当前对象（这里是 name）。
//因此let可以根据变量是否为空，决定程序的执行流程，非常安全，避免出现空指针异常。
//示例：安全地访问用户输入
fun sendGreeting(name: String?) {
    name?.let {
        println("Hello, $it!")  // 仅当 name 不为 null 时执行
    } ?: println("Hello, guest!")  // name 为 null，使用默认值
}

fun main() {
    val userName: String? = null
    sendGreeting(userName)  // 输出: Hello, guest!

    val anotherUser: String? = "Alice"
    sendGreeting(anotherUser)  // 输出: Hello, Alice!
}
//第2行代码name?.let { ... }只有name不为 null 时，才会执行println("Hello, $it!")。第4行代码中的?:是Elvis操作符，提供了null时的默认行为，是一种非常优雅的方式来处理用户输入可能为null的数据。