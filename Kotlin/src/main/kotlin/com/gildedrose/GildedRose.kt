package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach { item -> updateItemQuality(item) }
    }

    private fun updateItemQuality(item: Item) {
        if (item.name == "Sulfuras, Hand of Ragnaros") return

        if (item.name == "Aged Brie") {
            if (item.quality < 50) {
                item.quality++
            }
        } else if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
            if (item.quality < 50) {
                item.quality++
                if (item.sellIn < 11) {
                    if (item.quality < 50) {
                        item.quality++
                    }
                }
                if (item.sellIn < 6) {
                    if (item.quality < 50) {
                        item.quality++
                    }
                }
            }
        } else {
            if (item.quality > 0) {
                item.quality--
            }
        }

        item.sellIn--

        if (item.sellIn < 0) {
            if (item.name == "Aged Brie") {
                if (item.quality < 50) {
                    item.quality++
                }
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
    }

}

