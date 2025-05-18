#!/usr/bin/env kotlin

//4.5.6 单例类
//在软件开发中，单例模式（Singleton Pattern）是一种确保某个类在整个应用程序生命周期内只有一个实例的设计模式。Kotlin提供了简洁优雅的方式来实现单例模式，使得开发者不必手动管理实例化逻辑。
//（1）单例模式
//可以使用 object关键字实现单例类。由于object本身就是一个全局唯一的实例，无需额外编写构造函数或getInstance()方法。
object Database {
    val name = "MainDB"

    fun connect() {
        println("连接到数据库：$name")
    }
}

fun main() {
    Database.connect()  // 输出: 连接到数据库：MainDB
}
//第1行代码使用关键字object定义了一个单例对象Database，整个应用中只有一个Database实例。第10行代码直接调用Database.connect()，不需要创建Database实例。


//（2）线程安全的单例
//多线程环境下，单例可能在多个线程同时访问时被多次创建，可以使用lazy进行线程安全的初始化。
class Network private constructor() {
    companion object {
        val instance: Network by lazy { Network() }
    }

    fun request() {
        println("网络请求中...")
    }
}

fun main() {
    Network.instance.request()  // 输出: 网络请求中...
}

//第1行代码Network类的构造函数被private关键字修饰，禁止外部直接实例化，只能通过companion object访问。第2行代码companion object用于定义Network类的唯一实例。
//companion object是Kotlin用于在类中定义伴生对象的关键字。它类似于 Java的static成员，允许在类内部声明与类相关但不属于特定实例的属性和方法。伴生对象中的成员可以直接通过类名访问，而无需创建对象实例。这在需要定义工厂方法、静态常量或工具函数时非常有用。
//第3行代码by lazy让Network在第一次使用时才初始化（懒加载）。by lazy 是线程安全，确保多线程环境下不会创建多个实例。
//第12行代码Network.instance获取单例对象，可以直接调用request()。


//（3）需要构造函数的单例
//由于object不能有构造函数，如果单例需要接收参数，应该使用 companion object + lateinit 或 by lazy 来进行实例化。
class Config private constructor(val url: String) {
    companion object {
        @Volatile
        private var _url: String? = null  // 存储 URL

        val instance: Config by lazy {
            requireNotNull(_url) { "Config 未初始化，请先调用 init()" }
            Config(_url!!)
        }

        fun init(url: String) {
            if (_url == null) {
                _url = url
            }
        }
    }
}

fun main() {
    Config.init("https://api.example.com")  // 先初始化 URL
    println(Config.instance.url)  // 输出: https://api.example.com
}
//第3行代码的@Volatile，保证变量在多线程环境下的可见性，用于防止线程缓存变量导致数据不一致。第6行代码通过lazy实现懒加载，第一次访问时创建Config实例。第7行代码如果_url为空，抛出异常，防止实例未初始化。第11行代码允许外部调用Config.init("URL")设置 URL，只能设置一次。
//第20行代码先调用Config.init("URL")初始化，再访问Config.instance，触发lazy创建Config单例，最后打印url。
