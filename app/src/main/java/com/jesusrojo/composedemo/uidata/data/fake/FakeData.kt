package com.jesusrojo.composedemo.uidata.data.fake

import com.jesusrojo.composedemo.uidata.data.model.UiData
import com.jesusrojo.composedemo.uidata.data.model.UiDataIcon

class FakeData {

    companion object {

        fun getFakeListUiData(size: Int = 20): List<UiData> {
            val results = mutableListOf<UiData>()
            for (i  in 0..size) {
                val uiData = generateRandomUiData()
                results.add(i, uiData)
            }
            return results
        }

        fun generateRandomUiData(): UiData {
            var message = listOf(
                "Red",
                "Yellow",
                "Green",
                "Blue"
            ).random()
            message += " RANDOM: ${(0..100).random()}"
            val icon = UiDataIcon.values().random()
            return UiData(message, icon)
        }
    }
}