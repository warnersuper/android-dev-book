#!/usr/bin/env kotlin

//集合
//在 Kotlin 中，List 是一种有序的集合，允许存储重复元素，并可以通过索引 (list[index]) 访问元素。常见的 List 分为不可变列表 (listOf()) 和 可变列表 (mutableListOf())，不可变列表的元素不可修改，而可变列表支持添加、修改和删除元素
// 创建不可变列表（不可修改）
val list = listOf("Apple", "Banana", "Orange")
println(list[1]) // 输出：Banana

// 创建可变列表（可以修改）
val mutableList = mutableListOf("Kotlin", "Java")
mutableList.add("Python")  // 添加元素
println(mutableList) // 输出：[Kotlin, Java, Python]

//Set是一种无序集合，不允许存储重复元素，常用于去重或快速查找数据。Set分为不可变集合 (setOf()) 和 可变集合 (mutableSetOf())，不可变集合的元素不能修改，而可变集合支持添加和删除元素。
// 创建不可变 Set
val set = setOf("Apple", "Banana", "Apple")
println(set) // 输出：[Apple, Banana] （自动去重）

// 创建可变 Set
val mutableSet = mutableSetOf("Kotlin", "Java")
mutableSet.add("Python")  // 添加元素
mutableSet.add("Java")    // 添加重复元素，Set 不会存储
println(mutableSet) // 输出：[Kotlin, Java, Python]

//HashMap是一种键值对（key-value）映射的数据结构，提供高效的查找、添加和删除操作。键（key）是唯一的，而值（value）可以重复。HashMap 的常见创建方式包括不可变映射(mapOf())和可变映射(mutableMapOf())，可变映射支持动态修改
// 创建不可变 Map
val map = mapOf("name" to "Alice", "age" to 25)
println(map["name"]) // 输出：Alice

// 创建可变 HashMap
val mutableMap = mutableMapOf("A" to 1, "B" to 2)
mutableMap["C"] = 3  // 添加键值对
mutableMap.remove("A") // 删除键
println(mutableMap) // 输出：{B=2, C=3}

