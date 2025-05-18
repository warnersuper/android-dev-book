#!/usr/bin/env kotlin

//4.6.3集合操作
//集合操作函数是Kotlin中用于处理集合（如 List、Set、Map）的函数，属于标准库提供的一类强大工具。这些函数可以用来对集合中的元素进行筛选、转换、统计、聚合、查找、排序等操作，常配合Lambda表达式使用，使代码更简洁、表达更直接。


//示例：使用 map函数
val numbers = listOf(1, 2, 3)
val doubled = numbers.map { it * 2 }
println(doubled)  // 输出: [2, 4, 6]
//第1行代码创建一个不可变的整数列表numbers，包含元素 [1, 2, 3]。第二行代码调用了集合操作函数map，对numbers中的每个元素进行变换，it * 2表示将每个元素乘以2，这里返回一个新的列表doubled，不修改原始列表。

//示例：使用 filter + map函数
fun main() {
    val names = listOf("Alice", "Bob", "Charlie", "David")
    
    // 筛选长度 > 3 的名字，并转换为大写
    val filteredNames = names.filter { it.length > 3 }.map { it.uppercase() }
    
    println(filteredNames)  // 输出: [ALICE, CHARLIE, DAVID]
}
//第1行代码定义一个不可变字符串列表 names，包含四个名字。第5行代码是核心逻辑，分两步处理：① filter { it.length > 3 }过滤出长度大于3的名字，"Alice"（5）、"Charlie"（7）、"David"（5）符合条件，"Bob"（3）被排除。② map { it.uppercase() }把保留下来的名字全部转换为大写，变成 [ALICE, CHARLIE, DAVID]。

//示例：使用 filter + map + sum函数
fun main() {
    val nums = listOf(1, 2, 3, 4, 5)

    val result = nums
        .filter { it % 2 == 0 }     // 筛选偶数
        .map { it * it }           // 平方
        .sum()                     // 求和

    println(result)  // 输出: 20（即 4^2 + 2^2）
}
//这段代码展示了链式集合操作函数的使用流程：filter → map → sum。第1行代码创建一个包含整数的不可变列表 nums。第5行代码只保留偶数元素，结果是 [2, 4]。第6行代码将 [2, 4] 映射为它们的平方：[2², 4²] → [4, 16]。第7行代码计算 [4, 16] 的总和，得到20。
//示例：使用 filter + map + forEach函数
fun main() {
    val scores = listOf(45, 78, 62, 90, 55, 84, 33)

    scores
        .filter { it >= 60 } // 1. 筛选出及格成绩
        .map {               // 2. 转换为等级
            when {
                it >= 85 -> "A"
                it >= 70 -> "B"
                else -> "C"
            }
        }
        .forEach { grade ->  // 3. 遍历打印结果
            println("等级：$grade")
        }
}
//第5行代码filter保留满足条件的元素（>= 60），第6行到第12行代码将每个元素转换为新的值（数字 → 等级），第13行代码遍历集合中的每个元素，执行打印结果的动作。

//示例：any函数和all函数
fun main() {
    val numbers = listOf(2, 4, 6, 8)
    val hasEven = numbers.any { it % 2 == 0 }
    val allEven = numbers.all { it % 2 == 0 }

    println("包含偶数吗？$hasEven")  // 输出: 包含偶数吗？true
    println("全部是偶数吗？$allEven")  // 输出: 全部是偶数吗？true
}
//第3行代码检查是否有元素是偶数，第4行代码检查集合中是否每个元素都是偶数。all 用来验证集合中“全部元素都符合某条件”，any适合快速判断集合中是否“存在某种情况”。

//示例：find函数
fun main() {
    val names = listOf("Tom", "Alice", "Bob", "Charlie")

    val longName = names.find { it.length > 4 }

    println("第一个名字长度大于4的是：$longName")  // 输出: 第一个名字长度大于4的是：Alice
}

//第4行代码在names列表中寻找第一个长度大于4的名字，第一个符合条件的是 "Alice"，所以返回它，如果列表中没有符合条件的元素，返回结果将是null。