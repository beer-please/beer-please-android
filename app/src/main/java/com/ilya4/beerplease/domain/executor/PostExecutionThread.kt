package com.ilya4.beerplease.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {

    fun getScheluder() : Scheduler
}