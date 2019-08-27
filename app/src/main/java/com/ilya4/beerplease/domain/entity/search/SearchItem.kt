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
            listSearchItem.add(SearchItem("Балтика 2", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 2", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 2", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 2", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 2", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 2", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 2", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 2", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 2", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 2", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 2", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 2", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 2", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 2", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 2", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Балтика 2", "Лагер", "Балтика", 4.5f))
            listSearchItem.add(SearchItem("Крушовице", "Лагер", "noname", 4.5f))
            listSearchItem.add(SearchItem("Gose", "Лагер", "noname", 4.5f))
            listSearchItem.add(SearchItem("Garage", "Лагер", "noname", 4.5f))
            listSearchItem.add(SearchItem("Hougarden", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("387", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("Kult", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("просто пиво", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("Черниговское", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("Хамовники", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("Жатецкий Гусь", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("Paulaner", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("Guinness", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("Джой", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("Абаканское", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("Жигули", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("Жигулевское", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("Аян", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("Хуета", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("Spaten", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("Heineken", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("Велкопоповицкий козел", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("Miller", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("Leffe", "Лагер", "\"noname\"", 4.5f))
            listSearchItem.add(SearchItem("Не ебу какое ещё", "Лагер", "\"noname\"", 4.5f))

            return listSearchItem
        }
    }
}