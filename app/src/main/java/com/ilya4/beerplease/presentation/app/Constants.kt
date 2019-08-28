package com.ilya4.beerplease.presentation.app

object Constants {
    const val CONNECT_TIMEOUT : Long = 1000
    const val READ_TIMEOUT : Long = 10
    const val WRITE_TIMEOUT : Long = 10

    // Constants for permissions
    const val REQUEST_CAMERA = 1
    const val REQUEST_READ_WRITE_STORAGE = 2
    const val REQUEST_LOCATION = 3

    // Constants for backstack with BottomNavigationView
    const val ACTION = ".action"
    const val DATA_KEY_1 = ".data_key_1"
    const val DATA_KEY_2 = ".data_key_2"
}