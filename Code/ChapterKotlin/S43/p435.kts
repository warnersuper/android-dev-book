#!/usr/bin/env kotlin

//循环控制语句

//（1）break 语句：终止整个循环
//break语句用于立即终止当前循环，并跳出循环体，执行循环后的代码。当某个特定条件满足时，可以使用 break 提前退出循环。
//基本语法：
while (条件) {
        if (终止条件) {
                break
            }
        // 执行代码
    }

//示例：遇到特定值时终止循环
var i = 1
while (i <= 10) {
        if (i == 5) {
                break  // 当 i 等于 5 时，终止循环
            }
        println(i)
        i++
    }
// 输出：1 2 3 4

//（2）continue 语句：跳过当前迭代
//基本语法：
while (条件) {
        if (跳过条件) {
                continue
            }
        // 执行代码
    }

//示例：跳过特定值
for (i in 1..5) {
        if (i == 3) {
           continue  // 当 i 等于 3 时，跳过当前循环，进入下一次迭代
        }
       println(i)
    }
// 输出：1 2 4 5

//（3）嵌套循环
//示例：使用 break 终止外层循环
outer@ for (i in 1..3) {
        for (j in 1..3) {
                if (i == 2 && j == 2) {
                        break@outer  // 终止整个外层循环
                    }
               println("i=$i, j=$j")
           }
    }

//示例：使用 continue 跳过外层循环的某次迭代
outer@ for (i in 1..3) {
        for (j in 1..3) {
                if (i == 2 && j == 2) {
                        continue@outer  // 跳过 `i=2` 的所有后续 j 值，直接进入 `i=3`
                    }
                println("i=$i, j=$j")
            }
    }






