#!/usr/bin/env kotlin

//4.4.2 具名参数和默认参数

//在 Kotlin 中，调用函数时可以使用具名参数（Named Arguments）。具名参数允许在调用函数时显式指定参数名称，从而提高代码的可读性，避免因参数顺序错误而导致的调用问题。具名参数特别适用于参数较多且有默认值的函数，使调用更加清晰直观
fun greet(name: String, age: Int) {
        println("Hello, $name! You are $age years old.")
    }
greet("Tom", 18)                // 没有使用具名参数
greet(name = "Alice", age = 25)   // 使用具名参数，输出：Hello, Alice! You are 25 years old.
greet(age = 30, name = "Bob")   // 使用具名参数，参数顺序可变

//第4行代码没有使用具名参数，参数的顺序需要和函数定义一致。第5行和第6行代码使用了具名参数，指定了参数名称，则可以忽略参数的顺序
//默认参数（Default Arguments）允许在定义函数时为参数指定默认值，这样在调用函数时可以省略某些参数。默认参数减少了函数的重载需求，并提高了代码的可读性和可维护性
fun greet(name: String = "Guest") {
        println("Hello, $name!")
    }

greet()         // 输出：Hello, Guest!
greet("Alice")  // 输出：Hello, Alice!
//默认参数不需要定义多个重载版本的函数，只需提供默认值即可。调用时可以省略不必要的参数，使代码更清晰




