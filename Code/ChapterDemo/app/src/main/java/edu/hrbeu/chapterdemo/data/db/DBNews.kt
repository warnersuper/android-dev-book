package edu.hrbeu.chapterdemo.data.db

data class DBNews(
    var id: Int = -1,
    var title: String = "",
    var link: String = "",
    var description: String = "",
    var author: String = "",
    var pubDate: String = "",
    var hashCode: Int = 0,
)