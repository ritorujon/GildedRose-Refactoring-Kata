package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach { item -> updateItemQuality(item) }
    }

    private fun updateItemQuality(item: Item) {
        if (item.name == "Sulfuras, Hand of Ragnaros") return

        if (item.name == "Aged Brie") {
            item.increaseQuality(1)
            if (item.sellIn <= 0) {
                item.increaseQuality(1)
            }
        } else if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
            item.increaseQuality(1)
            if (item.sellIn < 11) {
                item.increaseQuality(1)
            }
            if (item.sellIn < 6) {
                item.increaseQuality(1)
            }
            if (item.sellIn <= 0) {
                item.quality = 0
            }
        } else {
            item.decreaseQuality(1)
            if (item.sellIn <= 0) {
                item.decreaseQuality(1)
            }
        }
        item.sellIn--
    }

    private fun Item.increaseQuality(increase: Int) {
        quality = minOf(quality + increase, 50)
    }

    private fun Item.decreaseQuality(decrease: Int) {
        quality = maxOf(quality - decrease, 0)
    }

}