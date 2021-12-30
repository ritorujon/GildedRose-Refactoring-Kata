package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    private fun updateItems(vararg items: Item): Array<Item> {
        val app = GildedRose(arrayOf(*items))
        app.updateQuality()
        return app.items
    }

    @Test
    fun normalItem_beforeSellBy() {
        val updatedItems = updateItems(Item("some normal item", 11, 11))
        assertEquals(10, updatedItems[0].sellIn, "Normal item - SellIn should be reduced by 1")
        assertEquals(10, updatedItems[0].quality, "Normal item - Quality should be reduced by 1")
    }

    @Test
    fun normalItem_afterSellBy() {
        val updatedItems = updateItems(Item("some normal item", 0, 11))
        assertEquals(-1, updatedItems[0].sellIn, "Normal item - SellIn should be reduced by 1")
        assertEquals(9, updatedItems[0].quality, "Normal item - Quality should be reduced by 2")
    }

    @Test
    fun normalItem_minimalQuality_beforeSellBy() {
        val updatedItems = updateItems(Item("some normal item", 11, 0))
        assertEquals(10, updatedItems[0].sellIn, "Normal item - SellIn should be reduced by 1")
        assertEquals(0, updatedItems[0].quality, "Normal item - Quality should stay at 0 (minimum)")
    }

    @Test
    fun normalItem_minimalQuality_afterSellBy() {
        val updatedItems = updateItems(Item("some normal item", 0, 0))
        assertEquals(-1, updatedItems[0].sellIn, "Normal item - SellIn should be reduced by 1")
        assertEquals(0, updatedItems[0].quality, "Normal item - Quality should stay at 0 (minimum)")
    }

    @Test
    fun agedItem_beforeSellBy() {
        val updatedItems = updateItems(Item("Aged Brie", 11, 11))
        assertEquals(10, updatedItems[0].sellIn, "Aged item - SellIn should be reduced by 1")
        assertEquals(12, updatedItems[0].quality, "Aged item - Quality should be increased by 1")
    }

    @Test
    fun agedItem_afterSellBy() {
        val updatedItems = updateItems(Item("Aged Brie", 0, 11))
        assertEquals(-1, updatedItems[0].sellIn, "Aged item - SellIn should be reduced by 1")
        assertEquals(13, updatedItems[0].quality, "Aged item - Quality should be increased by 2")
    }

    @Test
    fun agedItem_maximumQuality_beforeSellBy() {
        val updatedItems = updateItems(
            Item("Aged Brie", 1, 48),
            Item("Aged Brie", 11, 49),
            Item("Aged Brie", 11, 50)
        )
        assertEquals(0, updatedItems[0].sellIn, "Aged item [0] - SellIn should be reduced by 1")
        assertEquals(10, updatedItems[1].sellIn, "Aged item [1] - SellIn should be reduced by 1")
        assertEquals(10, updatedItems[2].sellIn, "Aged item [2] - SellIn should be reduced by 1")

        assertEquals(49, updatedItems[0].quality, "Aged item [0] - Quality should increased by 1")
        assertEquals(50, updatedItems[1].quality, "Aged item [1] - Quality should increase to 50 (maximum)")
        assertEquals(50, updatedItems[2].quality, "Aged item [2] - Quality should stay at 50 (maximum)")
    }

    @Test
    fun agedItem_maximumQuality_afterSellBy() {
        val updatedItems = updateItems(
            Item("Aged Brie", 0, 48),
            Item("Aged Brie", 0, 49),
            Item("Aged Brie", 0, 50)
        )
        assertEquals(-1, updatedItems[0].sellIn, "Aged item [0] - SellIn should be reduced by 1")
        assertEquals(-1, updatedItems[1].sellIn, "Aged item [1] - SellIn should be reduced by 1")
        assertEquals(-1, updatedItems[2].sellIn, "Aged item [2] - SellIn should be reduced by 1")

        assertEquals(50, updatedItems[0].quality, "Aged item [0] - Quality should increase to 50 (maximum)")
        assertEquals(50, updatedItems[1].quality, "Aged item [1] - Quality should increase to 50 (maximum)")
        assertEquals(50, updatedItems[2].quality, "Aged item [2] - Quality should stay at 50 (maximum)")
    }

    @Test
    fun legendaryItem_constantQuality_constantSellIn() {
        val updatedItems = updateItems(
            Item("Sulfuras, Hand of Ragnaros", 11, 80),
            Item("Sulfuras, Hand of Ragnaros", 0, 80),
            Item("Sulfuras, Hand of Ragnaros", -1, 80)
        )
        assertEquals(11, updatedItems[0].sellIn, "Legendary item [0] - SellIn should stay constant")
        assertEquals(0, updatedItems[1].sellIn, "Legendary item [1] - SellIn should stay constant")
        assertEquals(-1, updatedItems[2].sellIn, "Legendary item [2] - SellIn should stay constant")

        assertEquals(80, updatedItems[0].quality, "Legendary item [0] - Quality should stay constant")
        assertEquals(80, updatedItems[1].quality, "Legendary item [1] - Quality should stay constant")
        assertEquals(80, updatedItems[2].quality, "Legendary item [2] - Quality should stay constant")
    }

    @Test
    fun backstagePasses() {
        val updatedItems = updateItems(
            Item("Backstage passes to a TAFKAL80ETC concert", 20, 11),
            Item("Backstage passes to a TAFKAL80ETC concert", 11, 11),
            Item("Backstage passes to a TAFKAL80ETC concert", 10, 11),
            Item("Backstage passes to a TAFKAL80ETC concert", 5, 11),
            Item("Backstage passes to a TAFKAL80ETC concert", 1, 11),
            Item("Backstage passes to a TAFKAL80ETC concert", 0, 11),
            Item("Backstage passes to a TAFKAL80ETC concert", -1, 11)

        )
        assertEquals(19, updatedItems[0].sellIn, "Backstage passes [0] - SellIn should be reduced by 1")
        assertEquals(10, updatedItems[1].sellIn, "Backstage passes [1] - SellIn should be reduced by 1")
        assertEquals(9, updatedItems[2].sellIn, "Backstage passes [2] - SellIn should be reduced by 1")
        assertEquals(4, updatedItems[3].sellIn, "Backstage passes [3] - SellIn should be reduced by 1")
        assertEquals(0, updatedItems[4].sellIn, "Backstage passes [4] - SellIn should be reduced by 1")
        assertEquals(-1, updatedItems[5].sellIn, "Backstage passes [5] - SellIn should be reduced by 1")
        assertEquals(-2, updatedItems[6].sellIn, "Backstage passes [6] - SellIn should be reduced by 1")

        assertEquals(12, updatedItems[0].quality, "Backstage passes [0] - Quality should be increased by 1, when SellIn > 10")
        assertEquals(12, updatedItems[1].quality, "Backstage passes [1] - Quality should be increased by 1, when SellIn > 10")
        assertEquals(13, updatedItems[2].quality, "Backstage passes [2] - Quality should be increased by 2, when 10 >= SellIn > 5")
        assertEquals(14, updatedItems[3].quality, "Backstage passes [3] - Quality should be increased by 3, when 5 >= SellIn > 0")
        assertEquals(14, updatedItems[4].quality, "Backstage passes [4] - Quality should be reduced by 3, when 5 >= SellIn > 0")
        assertEquals(0, updatedItems[5].quality, "Backstage passes [5] - Quality should be set to 0 after the concert (SellIn <= 0)")
        assertEquals(0, updatedItems[6].quality, "Backstage passes [6] - Quality should be set to 0 after the concert (SellIn <= 0)")
    }

    @Test
    fun backstagePasses_maximumQuality() {
        val updatedItems = updateItems(
            Item("Backstage passes to a TAFKAL80ETC concert", 20, 50),
            Item("Backstage passes to a TAFKAL80ETC concert", 8, 49),
            Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
        )
        assertEquals(19, updatedItems[0].sellIn, "Backstage passes [0] - SellIn should be reduced by 1")
        assertEquals(7, updatedItems[1].sellIn, "Backstage passes [1] - SellIn should be reduced by 1")
        assertEquals(4, updatedItems[2].sellIn, "Backstage passes [2] - SellIn should be reduced by 1")

        assertEquals(50, updatedItems[0].quality, "Backstage passes [0] - Quality should stay at 50 (maximum)")
        assertEquals(50, updatedItems[1].quality, "Backstage passes [1] - Quality should increase to 50 (maximum)")
        assertEquals(50, updatedItems[2].quality, "Backstage passes [2] - Quality should increase to 50 (maximum)")
    }

}


