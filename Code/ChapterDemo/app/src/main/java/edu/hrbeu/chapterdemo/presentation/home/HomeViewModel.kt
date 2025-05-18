package edu.hrbeu.chapterdemo.presentation.home

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.hrbeu.chapterdemo.data.base.AppData
import edu.hrbeu.chapterdemo.data.db.DBAdapter
import edu.hrbeu.chapterdemo.data.db.DBNews
import edu.hrbeu.chapterdemo.data.net.Channel
import edu.hrbeu.chapterdemo.data.net.News
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.w3c.dom.Element
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.xml.parsers.DocumentBuilderFactory


class HomeViewModel: ViewModel() {

    val client = HttpClient(OkHttp) {
        defaultRequest {
            headers.append("User-Agent", "Mozilla/5.0")
            headers.append("Accept", "application/xml")
        }
    }

    private val _data = MutableStateFlow<List<News>>(emptyList())
    val data: StateFlow<List<News>> = _data

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadData(dbAdapter: DBAdapter, context:Context) {
        viewModelScope.launch {
            try {
                val response: HttpResponse = client.get(AppData.website)
                val xmlString = response.bodyAsText()
                val channel = parseXML(xmlString)
                _data.value = channel.items

                //数据存储
                if (AppData.IsAutoSave){
                    var counter = 0
                    channel.items.forEach {
                        println(" - ${it.title} (${it.link})")

                        val newsHashCode = it.title.hashCode()
                        var isExists = dbAdapter.exists(newsHashCode)

                        if (!isExists || !AppData.IsRemoveDuplicate) {
                            val dbNews = DBNews().apply {
                                this.title = it.title
                                this.link = it.link
                                this.author = it.author ?: ""
                                this.description = it.description
                                this.pubDate = it.pubDate
                                this.hashCode = it.title.hashCode()
                            }
                            dbAdapter.insert(dbNews)
                            counter += 1
                        }
                    }

                    Toast.makeText(context, "${counter}条数据存入数据库", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                // 可根据需求处理错误
                println("Error: $e")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        client.close() // 释放资源
    }

    //解析XML数据
    @RequiresApi(Build.VERSION_CODES.O)
    fun parseXML(xml: String): Channel {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val doc = builder.parse(xml.byteInputStream())
        doc.documentElement.normalize()

        val channel = doc.getElementsByTagName("channel").item(0) as Element

        val feedTitle = channel.getElementsByTagName("title").item(0).textContent
        val feedLink = channel.getElementsByTagName("link").item(0).textContent
        val feedDescription = channel.getElementsByTagName("description").item(0).textContent
        val feedPubDate = channel.getElementsByTagName("pubDate").item(0).textContent

        val itemNodes = channel.getElementsByTagName("item")
        val items = mutableListOf<News>()

        for (i in 0 until itemNodes.length) {
            val item = itemNodes.item(i) as Element
            val title = item.getElementsByTagName("title").item(0).textContent
            val link = item.getElementsByTagName("link").item(0).textContent
            val description = item.getElementsByTagName("description").item(0).textContent
            val author = item.getElementsByTagName("author")?.item(0)?.textContent
            val pubDate = formatDate(item.getElementsByTagName("pubDate").item(0).textContent)

            items.add(News(title, link, description, author, pubDate))
        }

        return Channel(feedTitle, feedLink, feedDescription, feedPubDate, items)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(dateStr: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern(
            "EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH
        )
        val dateTime = ZonedDateTime.parse(dateStr, inputFormatter)

        val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return dateTime.format(outputFormatter)
    }



}