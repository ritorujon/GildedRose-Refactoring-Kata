package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach { item -> updateItemQuality(item) }
    }

    private fun updateItemQuality(item: Item) {
        if (item.name == "Sulfuras, Hand of Ragnaros") return

        if (item.name == "Aged Brie") {
            item.increaseQuality(1)
        } else if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
            item.increaseQuality(1)
            if (item.sellIn < 11) {
                item.increaseQuality(1)
            }
            if (item.sellIn < 6) {
                item.increaseQuality(1)
            }
        } else {
            if (item.quality > 0) {
                item.quality--
            }
        }

        if (item.sellIn <= 0) {
            if (item.name == "Aged Brie") {
                item.increaseQuality(1)
            } else {
                if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                    item.quality = 0
                } else {
                    if (item.quality > 0) {
                        item.quality--
                    }
                }
            }
        }
        item.sellIn--
    }

    private fun Item.increaseQuality(increase: Int) {
        quality = minOf(quality + increase, 50)
    }

}

