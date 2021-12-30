package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach { item -> updateItemQuality(item) }
    }

    private fun updateItemQuality(item: Item) {
        when (item.name) {
            "Sulfuras, Hand of Ragnaros" -> {
                return
            }
            "Aged Brie" -> {
                if (item.sellIn > 0) {
                    item.increaseQuality(1)
                } else {
                    item.increaseQuality(2)
                }
            }
            "Backstage passes to a TAFKAL80ETC concert" -> {
                if (item.sellIn > 10) {
                    item.increaseQuality(1)
                } else if (item.sellIn > 5) {
                    item.increaseQuality(2)
                } else if (item.sellIn > 0) {
                    item.increaseQuality(3)
                } else {
                    item.quality = 0
                }
            }
            else -> {
                if (item.sellIn > 0) {
                    item.decreaseQuality(1)
                } else {
                    item.decreaseQuality(2)
                }
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