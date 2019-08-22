package com.ilya4.beerplease.domain.entity.search

data class SearchItem(
   val name: String,
   val gradeBeer: String,
   val companyName: String,
   val rating: Float) {

    companion object {
        fun getListSearchItems(): ArrayList<SearchItem> {
            val listSearchItem = ArrayList<SearchItem>()
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 1", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 2", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 3", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 4", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 5", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 7", "Лагер", "Балтика", 4.5f))

            return listSearchItem
        }
    }
}