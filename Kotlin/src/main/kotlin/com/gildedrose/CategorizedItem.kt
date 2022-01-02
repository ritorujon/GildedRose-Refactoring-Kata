package com.gildedrose

abstract class CategorizedItem(private val item: Item) {
    var quality
        get() = item.quality
        protected set(value) {
            item.quality = value
        }

    var sellIn
        get() = item.sellIn
        protected set(value) {
            item.sellIn = value
        }

    fun ageOneDay() {
        updateQuality()
        updateSellIn()
    }

    protected open fun updateSellIn() {
        sellIn--
    }

    protected abstract fun updateQuality()

    protected fun increaseQuality(increase: Int) {
        quality = minOf(quality + increase, 50)
    }

    protected fun decreaseQuality(decrease: Int) {
        quality = maxOf(quality - decrease, 0)
    }
}

class LegendaryItem(item: Item) : CategorizedItem(item) {
    override fun updateQuality() {}
    override fun updateSellIn() {}
}

class AgedItem(item: Item) : CategorizedItem(item) {
    override fun updateQuality() {
        if (sellIn > 0) increaseQuality(1) else increaseQuality(2)
    }
}

class BackstagePass(item: Item) : CategorizedItem(item) {
    override fun updateQuality() {
        when {
            sellIn > 10 -> increaseQuality(1)
            sellIn > 5 -> increaseQuality(2)
            sellIn > 0 -> increaseQuality(3)
            else -> quality = 0
        }
    }
}

class ConjuredItem(item: Item) : CategorizedItem(item) {
    override fun updateQuality() {
        if (sellIn > 0) decreaseQuality(2) else decreaseQuality(4)
    }
}

class NormalItem(item: Item) : CategorizedItem(item) {
    override fun updateQuality() {
        if (sellIn > 0) decreaseQuality(1) else decreaseQuality(2)

    }
}