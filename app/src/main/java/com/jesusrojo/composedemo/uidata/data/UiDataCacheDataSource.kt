package com.jesusrojo.composedemo.uidata.data

import com.jesusrojo.composedemo.uidata.data.model.UiData

class UiDataCacheDataSource {

    private var datas = ArrayList<UiData>()

    fun fetchAllFromCache(): List<UiData> {
        return datas
    }

    fun saveAllToCache(datas: List<UiData>) {
        this.datas.clear()
        this.datas = ArrayList(datas)
    }

    suspend fun deleteAllInCache() {
        datas.clear()
    }

    fun saveOne(item: UiData) {
        datas.add(item)
    }

    fun removeOne(item: UiData) {
        datas.remove(item)
    }
}