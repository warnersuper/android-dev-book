#!/usr/bin/env kotlin

//4.5.1 类与对象

//在 Kotlin 中，类是对象的模板，用于描述对象的属性（成员变量）和行为（成员函数）。对象（Object）是类的具体实例，它代表真实存在的个体，每个对象都拥有自己的一组数据和行为
//（1）定义类
//语法如下：
class 类名 {
        // 属性（成员变量）
       // 方法（成员函数）
    }




//使用class关键字定义一个类，类内的内部定义成员变量和成员函数
//示例 1：定义一个简单的 Person 类
class Person {
        var name: String = "Unknown"
        var age: Int = 0

        fun introduce() {
                println("Hi, my name is $name and I am $age years old.")
            }
    }

//上述代码定义了一个Person类，包含两个属性name和age，以及一个成员函数introduce()。


//（2）创建对象
//Kotlin在创建类的对象时，省略了new关键字。
val person = Person()  // 创建对象
person.name = "Alice"
person.age = 25
person.introduce()  
// 输出：Hi, my name is Alice and I am 25 years old.

//创建对象（第1行代码）后，对对象的成员属性进行赋值（第2行和第3行代码）。最好调用成员函数（第4行代码）。


//（3） 构造函数
//Kotlin 提供了主构造函数和次构造函数。
//主构造函数是类头部声明的一部分，用于定义类的主要属性，并且可以在实例化对象时直接传递参数进行初始化。它的声明紧随class关键字之后，可以直接定义属性。
//主构造函数：
class Person(val name: String, val age: Int) {
   fun introduce() {
        println("Hi, my name is $name and I am $age years old.")
    }
}
//第1行代码就是类Person的主构造函数，在主构造函数中出现了name和age两个参数，这两个参数直接视为类的属性。
//创建对象时可以直接传入参数：
val person = Person("Alice", 25)
person.introduce()  
//输出：Hi, my name is Alice and I am 25 years old.
//由于主构造函数不能包含任何代码逻辑，因此通常结合init代码块用于初始化操作。
class Person(val name: String, val age: Int) {
    val info:String
    init{
            Info = "Person name: $name, age:$age"
    }
    fun introduce() {
        println(Info)
    }
}
//第3行到第5行，是初始化操作的代码块。在这个代码块中，将属性name和age进行字符串拼接，初始化了成员属性info。
//次构造函数使用constructor关键字定义，允许在类内部提供额外的初始化方式。它们必须显式调用主构造函数（如果存在），通常用于提供不同的初始化逻辑。
//次构造函数：
class Person(val name: String, val age: Int) {
    val info: String
    
    init {
        info = "Person name: $name, age: $age"
    }
    
    constructor(name: String) : this(name, 0) {
        println("Age is not provided, setting to default 0")
    }
    
    fun introduce() {
        println(info)
    }
}
//这样，既可以通过Person("Alice", 25)传入完整信息，也可以使用 Person("Bob") 仅提供姓名，并默认age为0。第8行代码中this(name,0)表示调用主构造函数。


//（4） 访问修饰符
//在 Kotlin 中，访问修饰符用于控制类、函数、变量的可见性，即哪些代码可以访问它们。Kotlin 提供了四种访问修饰符，每种修饰符适用于不同的访问场景。
//表4.3 Kotlin的四种访问修饰符
//修饰符	访问范围	适用场景	能否被子类访问	能否跨模块访问
public (默认)	任何地方都可访问	对外公开 API、工具类	是	是
private	仅限当前类 / 文件	内部逻辑封装，防止外部访问	否	否
protected	仅限当前类和子类	继承体系中的封装	是	否
internal	仅限同一模块内	限制 API 访问范围	是	否






