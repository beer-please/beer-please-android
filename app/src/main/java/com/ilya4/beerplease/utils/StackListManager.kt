package com.ilya4.beerplease.utils

import java.util.*

class StackListManager {

    companion object {

        /**
         * Keeps track of clicked tabs and their respective stacks
         * Swaps the tabs to first position as they're clicked
         * Ensures proper navigation when back presses occur
         */
        fun updateStackIndex(list: MutableList<String>, tabId: String) {
            while (list.indexOf(tabId) != 0) {
                val i = list.indexOf(tabId)
                Collections.swap(list, i, i - 1)
            }
        }
        /**
         * Keeps track of when switching between tabs occur
         * The next tab to be shown is pushed on top
         * The tab which was current before is now pushed as last
         */
        fun updateStackToIndexFirst(stackList: MutableList<String>, tabId: String) {
            val stackListSize =stackList.size
            var moveUp = 1
            while (stackList.indexOf(tabId) != stackListSize - 1) {
                val i = stackList.indexOf(tabId)
                Collections.swap(stackList, moveUp++, i)
            }
        }
        /**
         * Keeps track of the clicked tabs and ensures proper navigation if there are no nested fragments in the tabs
         * When navigating back, the user will end up on the first clicked tab
         * If the first tab is clicked again while navigating, the user will end up on the second tab clicked
         */
        fun updateTabStackIndex(tabList: MutableList<String>, tabId: String) {
            if (!tabList.contains(tabId)) {
                tabList.add(tabId)
            }
            while (tabList.indexOf(tabId) != 0) {
                val i = tabList.indexOf(tabId)
                Collections.swap(tabList, i, i - 1)
            }
        }
    }
}