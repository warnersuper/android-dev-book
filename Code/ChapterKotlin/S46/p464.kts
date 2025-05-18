#!/usr/bin/env kotlin

//4.6.4扩展函数
//扩展函数（Extension Function）是Kotlin提供的一种在不修改原类的情况下，为现有类添加新功能的方法。这样，可以对已有的类（包括标准库类和第三方库类）增加新的方法，而无需继承或修改源代码。
//（1）定义扩展函数
//扩展函数的定义格式如下：
fun 类名.函数名(参数): 返回类型 {
    // 函数体
}

// 示例：为String类型添加一个扩展函数，统计字符串中a字母的数量
import kotlin.math.sqrt

// 定义一个矩形类
class Rectangle(val width: Double, val height: Double)

// 定义扩展函数，计算矩形的对角线长度
fun Rectangle.diagonal(): Double {
    return sqrt(width * width + height * height)
}

fun main() {
    val rect = Rectangle(3.0, 4.0)
    println("矩形的对角线长度: ${rect.diagonal()}")  // 输出: 矩形的对角线长度: 5.0
}
//第4行代码定义Rectangle类，包含width（宽度）和height（高度）两个属性。第7行代码定义了扩展函数，其中Rectangle是扩展的目标类，diagonal()是新扩展的函数。在第12行代码中，创建了矩形对象，就可以直接调用新增的函数diagonal()计算矩形的对角线长度。这样，无需修改Rectangle类本身，就能扩展其功能，使其支持计算对角线。


//（2）扩展标准库类
//扩展函数用于扩展Kotlin标准库的类，或者用于扩展第三方的类，才是扩展函数的主要用途。
fun List<Int>.sumOdd(): Int {
    return this.filter { it % 2 != 0 }.sum()
}

fun main() {
    val numbers = listOf(1, 2, 3, 4, 5)
    println(numbers.sumOdd())  // 输出: 9 (1+3+5)
}

//第1行代码在List<Int>上扩展了计算奇数累加的函数sumOdd()，这样就扩展了原有库的功能。


//（3）扩展函数的作用范围
//扩展函数不能访问类的私有（private）或受保护（protected）成员，只能访问公开（public）的成员。
class Person(val name: String, private val age: Int)

fun Person.getNameLength(): Int {
    return this.name.length
}

fun main() {
    val p = Person("Kotlin", 25)
    println(p.getNameLength())  // 输出: 6
}
//第1行代码的构造函数中，指定name是公开的，而age是私有的。因此，在第4行代码中，通过this.name访问到name是可以的，而扩展函数无法访问age。


//（4）扩展函数和成员函数
//如果类本身有一个相同签名的成员函数，则成员函数优先级更高，扩展函数会被忽略。
class Sample {
    fun hello() {
        println("成员函数: Hello from class!")
    }
}

fun Sample.hello() {
    println("扩展函数: Hello from extension!")
}

fun main() {
    val sample = Sample()
    sample.hello()  // 输出: 成员函数: Hello from class!
}
//扩展函数不会覆盖已有的方法，调用的是原本的hello()成员方法。


//（5）扩展伴生对象
//扩展函数也可以用于伴生对象（companion object），类似于静态方法。
class Utils {
    companion object {}
}

fun Utils.Companion.showMessage() {
    println("Hello, Kotlin!")
}

fun main() {
    Utils.showMessage()  // 输出: Hello, Kotlin!
}

//showMessage()直接作为companion object的扩展方法调用。
