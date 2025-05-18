package edu.hrbeu.chapterdemo.presentation.setting

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.hrbeu.chapterdemo.data.base.AppData
import edu.hrbeu.chapterdemo.data.db.DBAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class SettingViewModel(): ViewModel() {
    var autoSaveChecked by mutableStateOf(true)
    var removeDuplicatesChecked by mutableStateOf(true)

    private val _dbTableCount = MutableStateFlow(0)
    val dbTableCount: StateFlow<Int> = _dbTableCount

    fun loadDBTableCount(dbAdapter: DBAdapter) {
        viewModelScope.launch {
            _dbTableCount.value = dbAdapter.getCount()
        }
    }

    fun observeAutoSaveAndDuplicates(context: Context) {
        viewModelScope.launch {

            //读取数据
            loadSharedPreferences(context).also {
                autoSaveChecked = it.isAutoSave
                removeDuplicatesChecked = it.isRemoveDuplicate
                println("读取到的数据，isAutoSave：${it.isAutoSave}, isRemoveDuplicate:${it.isRemoveDuplicate}")
            }

            //监视数据变化，并保存数据
            snapshotFlow { Pair(autoSaveChecked,removeDuplicatesChecked) }
                .distinctUntilChanged()
                .collect { (autoSave, removeDup) ->
                    saveSharedPreferences(context, autoSave, removeDup)
                    AppData.IsAutoSave = autoSave
                    AppData.IsRemoveDuplicate = removeDup
                }
        }
    }

}

