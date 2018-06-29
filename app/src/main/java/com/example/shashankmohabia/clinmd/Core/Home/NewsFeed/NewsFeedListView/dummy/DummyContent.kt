package com.example.shashankmohabia.clinmd.Core.Home.NewsFeed.dummy

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<DummyItem> = ArrayList<DummyItem>()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, DummyItem> = HashMap<String, DummyItem>()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createDummyItem(i))
        }
    }

    private fun addItem(item: DummyItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.title, item)
    }

    private fun createDummyItem(position: Int): DummyItem {
        return DummyItem("Item " + position,"Content " + position )
    }

    /**
     * A dummy item representing a piece of content.
     */
    class DummyItem(val title: String, val content: String) {

        public override fun toString(): String {
            return content
        }
    }
}