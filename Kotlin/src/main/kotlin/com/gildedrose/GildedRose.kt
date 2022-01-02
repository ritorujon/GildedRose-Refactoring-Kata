package com.gildedrose

class GildedRose(var items: Array<Item>) {
    fun updateQuality() {
        items.forEach { item ->
            toCategorizedItem(item).ageOneDay()
        }
    }

    private fun toCategorizedItem(item: Item): CategorizedItem {
        return when (item.name) {
            in "Sulfuras.*".toRegex() -> LegendaryItem(item)
            in "Aged.*".toRegex() -> AgedItem(item)
            in "Backstage pass.*".toRegex() -> BackstagePass(item)
            in "Conjured.*".toRegex() -> ConjuredItem(item)
            else -> NormalItem(item)
        }
    }
}

operator fun Regex.contains(text: CharSequence): Boolean = this.matches(text)