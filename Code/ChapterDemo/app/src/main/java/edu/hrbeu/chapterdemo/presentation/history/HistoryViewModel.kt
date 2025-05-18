package edu.hrbeu.chapterdemo.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.hrbeu.chapterdemo.data.base.AppData
import edu.hrbeu.chapterdemo.data.db.DBAdapter
import edu.hrbeu.chapterdemo.data.db.DBNews
import edu.hrbeu.chapterdemo.data.net.News
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryViewModel: ViewModel() {
    private val _data = MutableStateFlow<List<DBNews>>(emptyList())
    val data: StateFlow<List<DBNews>> = _data


    fun loadData(dbAdapter: DBAdapter){
        viewModelScope.launch {
            val resultArray = dbAdapter.queryAllData()
            _data.value = resultArray?.toList() ?: emptyList()
            println("从数据库获取到的数据量：${_data.value.size}")
        }
    }
}