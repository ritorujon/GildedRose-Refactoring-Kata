package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach { item -> updateItemQuality(item) }
    }

    private fun updateItemQuality(item: Item) {
        when (item.name) {
            "Sulfuras, Hand of Ragnaros" -> return
            "Aged Brie" -> updateAgedItemQuality(item)
            "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePassQuality(item)
            else -> updateNormalItemQuality(item)
        }
        item.sellIn--
    }

    private fun updateAgedItemQuality(item: Item) {
        if (item.sellIn > 0) item.increaseQuality(1) else item.increaseQuality(2)
    }

    private fun updateBackstagePassQuality(item: Item) {
        when {
            item.sellIn > 10 -> item.increaseQuality(1)
            item.sellIn > 5 -> item.increaseQuality(2)
            item.sellIn > 0 -> item.increaseQuality(3)
            else -> item.quality = 0
        }
    }

    private fun updateNormalItemQuality(item: Item) {
        if (item.sellIn > 0) item.decreaseQuality(1) else item.decreaseQuality(2)
    }

    private fun Item.increaseQuality(increase: Int) {
        quality = minOf(quality + increase, 50)
    }

    private fun Item.decreaseQuality(decrease: Int) {
        quality = maxOf(quality - decrease, 0)
    }

}