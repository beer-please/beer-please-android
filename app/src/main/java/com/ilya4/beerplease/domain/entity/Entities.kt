package com.ilya4.beerplease.domain.entity


data class Beer(
    val id: Int,
    val barcode: Int,
    val name: String,
    val title: String,
    val description: String,
    val alcoholByVolume: Float,
    val bitterness: Int)