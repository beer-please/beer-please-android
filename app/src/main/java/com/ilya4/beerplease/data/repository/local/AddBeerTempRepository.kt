package com.ilya4.beerplease.data.repository.local

import com.ilya4.beerplease.data.repository.BaseTempRepository

class AddBeerTempRepository: BaseTempRepository {
    var nameBeer: String? = null
    var brewery: String? = null
    var styleBeer: String? = null
    var abv: Float? = null
    var ibu: Int? = null
}