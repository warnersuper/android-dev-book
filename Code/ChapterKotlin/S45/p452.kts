#!/usr/bin/env kotlin

//4.5.2继承与多态
//Kotlin默认所有类都是不能被继承，要允许一个类被继承，需要使用 open 关键字。多态（Polymorphism）允许子类重写父类的方法，并通过父类引用调用子类实现，提高代码的扩展性和复用性。
//（1）open 关键字
//在Kotlin中，必须使用 open 关键字声明可继承的类：
// 允许被继承的类
open class Animal(val name: String) {
    fun makeSound() {
        println("$name 发出了声音")
    }
}

// 继承 Animal
class Dog(name: String) : Animal(name)

val dog = Dog("旺财")
dog.makeSound()  // 输出: 旺财 发出了声音
//第2行代码用关键字open允许Animal被继承。第9行代码Dog继承Animal。


//（2）方法重写
//如果子类需要修改父类方法的实现，父类的方法加open使其可重写。子类用override进行重写。
open class Animal {
    open fun makeSound() {
        println("动物发出了声音")
    }
}

class Dog : Animal() {
    override fun makeSound() {
        println("汪汪汪！")
    }
}

val animal: Animal = Dog() 
animal.makeSound()  // 输出: 汪汪汪！
//第2行代码使用关键字open使makeSound()函数可被重写。第8行代码使用关键字override重写makeSound()函数，让Dog实现自己的makeSound()逻辑。Animal类型变量animal调用了Dog的实现，体现了多态。


（3）super关键字
//子类中是可以调用父类的方法的，需要使用super关键字，实现调用父类方法。
open class Animal {
    open fun makeSound() {
        println("动物的默认叫声")
    }
}

class Cat : Animal() {
    override fun makeSound() {
        super.makeSound() // 先调用父类的方法
        println("喵喵喵！")
    }
}

val cat = Cat()
cat.makeSound()
//第9行代码super.makeSound()先调用Animal的makeSound()方法。第10行代码println("喵喵喵！")再输出子类的额外逻辑。


//（4）构造函数与继承
//子类必须调用父类的构造函数，可以使用“函数名称: super(...)” 传递参数。
open class Animal(val name: String) {
    open fun makeSound() {
        println("$name 发出了声音")
    }
}

class Bird(name: String, val color: String) : Animal(name) {
    override fun makeSound() {
        println("$color的$name叽叽喳喳！")
    }
}

val bird = Bird("鹦鹉", "绿色")
bird.makeSound()  //输出：绿色的鹦鹉叽叽喳喳！
//第7行代码Bird继承Animal，调用Animal(name)传递name参数传递到父类构造函数中。因为Animal的构造函数中有一个字符串参数，因此Bird类必须将一个字符串参数传递到父类的构造函数中。


//（5）抽象类
//抽象类（abstract class）是用于定义通用行为的类，它不能被直接实例化，必须由子类继承并实现其抽象成员。抽象类可以包含已实现的方法和未实现的抽象方法，使用 abstract 关键字声明。
abstract class Animal(val name: String) {
    abstract fun makeSound() // 抽象方法，没有实现
    fun describe() { // 已实现的方法
        println("这是一只名叫 $name 的动物。")
    }
}

class Dog(name: String) : Animal(name) {
    override fun makeSound() {
        println("$name 叫道：汪汪汪！")
    }
}

// val animal = Animal("未知")  报错，不能实例化抽象类
val dog = Dog("旺财")
dog.makeSound() // 输出: 旺财 叫道：汪汪汪！
dog.describe()  // 输出: 这是一只名叫 旺财 的动物。
//第1行代码使用关键字abstract，表示Animal类是一个抽象类，不能直接创建对象，只能被继承。第2行代码表示makeSound()表示该函数必须在子类中实现。第3行的describe()函数，已在Animal抽象类中实现，这样在继承类Dog中可以不再实现。



