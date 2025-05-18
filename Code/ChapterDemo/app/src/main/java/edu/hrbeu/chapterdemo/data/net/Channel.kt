package edu.hrbeu.chapterdemo.data.net

data class Channel(
    val title: String,
    val link: String,
    val description: String,
    val pubDate: String,
    val items: List<News>
)
