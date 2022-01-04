package com.gildedrose

class GildedRose(var items: Array<Item>) {
    fun updateQuality() {
        items.forEach { item ->
            CategorizedItem.from(item).ageOneDay()
        }
    }
}