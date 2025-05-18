#!/usr/bin/env kotlin

//4.5.3 接口与抽象类
//接口（interface）在 用于定义一组行为规范，而无需提供具体实现。它可以被多个类实现，从而实现代码的解耦和复用。接口可以包含抽象方法（无方法体）和默认实现的方法，同时不保存任何状态。与抽象类不同，接口支持多重继承，使得一个类可以同时实现多个接口，从而增强代码的灵活性。在面向对象编程中，接口常用于定义通用行为，例如可移动对象的move()方法或可点击对象的click()方法，使不同的类可以共享相同的行为约定。
//（1）定义接口
interface Animal {
    fun makeSound() // 抽象方法，无需 `open`
}
//接口中的方法默认是抽象的（abstract），不需要open关键字。在上面的代码中定义了Animal接口，任何实Animal接口的类，都必须提供makeSound()方法的实现。


//（2）实现接口
//类通过“:”继承并实现接口的方法。
interface Animal {
    fun makeSound()
}
class Dog : Animal {
    override fun makeSound() {
        println("汪汪汪！")
    }
}
fun main() {
    val dog = Dog()
    dog.makeSound()  // 输出: 汪汪汪！
}
//Dog类实现了Animal接口，并提供了makeSound()方法的具体实现。需要主义的是，第5行代码的override关键字，必须用于重写接口的方法。


//（3）接口中的默认实现
//Kotlin允许接口提供方法的默认实现，这样实现类可以直接使用，或进行重写。
interface Animal {
    fun makeSound() {
        println("默认叫声")
    }
}

class Cat : Animal // 没有重写方法，使用默认实现

class Dog : Animal {
    override fun makeSound() {
        println("汪汪汪！")
    }
}
fun main() {
    val cat = Cat()
    cat.makeSound() // 输出: 默认叫声

    val dog = Dog()
    dog.makeSound() // 输出: 汪汪汪！
}
//第2行代码Animal接口提供了makeSound()的默认实现，Cat直接使用了makeSound()的默认实现。Dog选择重写makeSound()，定义自己的行为。


//（4）接口中的属性
//接口可以定义属性，但不能存储状态（即不能初始化），只能是val（只读属性），不能是var。
interface Animal {
    val name: String // 不能赋初值，必须由子类提供
}

class Dog(override val name: String) : Animal

fun main() {
    val dog = Dog("旺财")
    println(dog.name)  // 输出: 旺财
}
//第2行代码里定义了name属性，但没有初始化，需要子类提供。Dog通过 override val name: String实现接口的name属性。
//（5）多接口实现
//Kotlin允许一个类实现多个接口，但如果多个接口有相同的方法，子类必须显式指定实现。
interface Animal {
    fun makeSound() {
        println("动物叫声")
    }
}

interface Pet {
    fun makeSound() {
        println("宠物叫声")
    }
}

class Dog : Animal, Pet {
    override fun makeSound() {
        super<Animal>.makeSound()  // 指定调用 Animal 的方法
        super<Pet>.makeSound()     // 指定调用 Pet 的方法
        println("汪汪汪！")  
    }
}
fun main() {
    val dog = Dog()
    dog.makeSound()
}
Dog 同时继承了Animal和Pet两个接口，它们都有makeSound()方法。必须用super<接口名>.方法名()解决冲突，指定调用哪个父接口的方法。


