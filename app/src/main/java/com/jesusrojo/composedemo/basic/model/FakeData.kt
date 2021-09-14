package com.jesusrojo.composedemo.basic.model

class FakeData {

    companion object {

        fun getFakeData(size: Int = 20): List<UiData> {

            val results = mutableListOf<UiData>()
            for (i  in 0..size) {
                val uiData = UiData("UiData $i, RANDOM: ${(0..100).random()}")
                results.add(i, uiData)
            }
            return results
        }

//        private fun getListNumbers():List<String>  {
//            val numbers: List<String> = List(1000) { "Hello Android #$it" }
//            return numbers
//        }
    }
}