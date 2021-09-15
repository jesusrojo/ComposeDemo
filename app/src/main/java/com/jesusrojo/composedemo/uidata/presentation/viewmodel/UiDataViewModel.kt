package com.jesusrojo.composedemo.uidata.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jesusrojo.composedemo.uidata.data.UiDataCacheDataSource
import com.jesusrojo.composedemo.uidata.data.fake.FakeData
import com.jesusrojo.composedemo.uidata.data.model.UiData

class UiDataViewModel : ViewModel() {

    private val dataSource: UiDataCacheDataSource = UiDataCacheDataSource()
    private var currentEditPosition by mutableStateOf(-1)

    var uiDatas = mutableStateListOf<UiData>()
        private set

    val currentEditItem: UiData?
        get() = uiDatas.getOrNull(currentEditPosition)

    fun addItem(item: UiData) {
        // uiDatas.add(item)
        dataSource.saveOne(item)
        fetchDatas()
    }

    fun removeItem(item: UiData) {
        dataSource.removeOne(item)
        fetchDatas()
       // uiDatas.remove(item)
        onEditDone() // don't keep the editor open when removing items
    }

    fun onEditItemSelected(item: UiData) {
        currentEditPosition = uiDatas.indexOf(item)
    }

    fun onEditDone() {
        currentEditPosition = -1
    }

    fun onEditItemChange(item: UiData) {
        val currentItem = requireNotNull(currentEditItem)
        require(currentItem.id == item.id) {
            "You can only change an item with the same id as currentEditItem"
        }

        uiDatas[currentEditPosition] = item
    }

    init {
        fetchDatas()
    }

    private fun fetchDatas() {
        val datas = dataSource.fetchAllFromCache()
        if(datas != null) {
            uiDatas.clear()
            uiDatas.addAll(datas)
        }
    }
}