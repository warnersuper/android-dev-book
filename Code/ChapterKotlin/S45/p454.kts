#!/usr/bin/env kotlin

//4.5.4 数据类 
//Kotlin 的数据类（data class）专为存储数据而设计，相较于普通类，它具有多个优势。首先，数据类自动生成 equals()、hashCode() 和 toString()方法，使得对象比较、哈希存储（如在集合中使用）以及调试时的可读性更高。其次，它还提供了copy()方法，便于创建对象的修改副本，而无需手动创建新实例。
//此外，数据类支持解构声明，可以快速提取对象的属性值，提高代码的可读性和简洁性。最后，数据类与componentN()方法配合使用，使得在函数返回多个值时更加直观。因此，数据类在数据封装、值对象（Value Object）和数据传输（DTO）等场景下具有显著优势。
//（1）基本数据类
//定义一个数据类很简单，只需要在class关键字前加上data关键字，例如：
data class User(val name: String, val age: Int)

val user = User("Alice", 25)
println(user)  // 输出: User(name=Alice, age=25)
//第1行代码定义了一个 User 数据类，并自动生成了toString()方法，使第4行代码打印user时，能清晰展示数据内容。



//（2）copy()方法
copy() 方法允许我们基于已有对象创建一个新对象，并修改其中部分属性。
val user1 = User("Alice", 25)
val user2 = user1.copy(age = 30)

println(user1) // 输出: User(name=Alice, age=25)
println(user2) // 输出: User(name=Alice, age=30)
//第2行代码copy(age = 30)生成一个新的User对象，但只修改了age属性，name仍然是 "Alice"。


//（3）equals() 和 hashCode()
//数据类会自动生成equals()和hashCode()，使得相同属性值的对象被认为是相等的。
val user1 = User("Alice", 25)
val user2 = User("Alice", 25)

println(user1 == user2) // 输出: true
println(user1 === user2) // 输出: false
//第4行代码中，==比较的是内容，结果为true，说明两个对象的内容相同。第5行代码中，===比较的是内存地址，两个对象是不同的实例，结果为false。


//（4）解构声明
//数据类支持解构赋值，可以用componentN()方法提取属性。componentN()是Kotlin数据类自动生成的一组方法，它用于解构对象，可以方便地按属性顺序提取数据类的值。例如，component1()返回第一个属性，component2()返回第二个属性，以此类推。通常，这些方法在解构声明（val (a, b) = obj）时被自动调用，使代码更简洁直观。
val user = User("Alice", 25)

val (name, age) = user
println("Name: $name, Age: $age")
//输出：Name: Alice, Age: 25
//第3行代码直接将user对象的属性解构成两个变量name和age。等效于下面的两组代码：
val user = User("Alice", 25)
val name = user.component1()
val age = user.component2()

val user = User("Alice", 25)
val name = user.name
val age = user.age


//（5）数据类限制
//数据类必须符合以下规则：
//1、必须至少有一个属性：
data class Empty() // 编译错误：必须至少有一个属性

//2、主构造函数的参数必须声明为val或var：
data class Person(name: String) // 错误：name 不是 val/var
//正确写法：
data class Person(val name: String) // 正确

//3、不能是 abstract、open、sealed 或 inner：
abstract data class Person(val name: String) // 错误：数据类不能是抽象的

