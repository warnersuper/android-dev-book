package edu.hrbeu.chapterjetpack.demo6

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class UserViewModel : ViewModel() {
    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    private val _userInfo = MutableStateFlow<UserInfo?>(null)
    val userInfo: StateFlow<UserInfo?> = _userInfo

    fun fetchUserInfo(username: String) {
        viewModelScope.launch {
            try {
                val user = client.get("https://api.github.com/users/$username")
                    .body<UserInfo>()
                _userInfo.value = user
            } catch (e: Exception) {
                Log.e("UserViewModel", "网络请求失败", e)
            }
        }
    }

    override fun onCleared() {
        client.close()
        super.onCleared()
    }
}
