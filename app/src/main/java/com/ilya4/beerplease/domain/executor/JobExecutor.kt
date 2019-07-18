package com.ilya4.beerplease.domain.executor

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class JobExecutor @Inject constructor () : ThreadExecutor {

    private val threadPoolExecutor : ThreadPoolExecutor
    init {
            threadPoolExecutor = ThreadPoolExecutor(3,5,10, TimeUnit.SECONDS,
            LinkedBlockingQueue(), JobThreadFactory())
    }


    override fun execute(command: Runnable?) {
        threadPoolExecutor.execute(command)
    }

    private class JobThreadFactory : ThreadFactory {

        private var  counter = 0

        override fun newThread(r: Runnable?): Thread = Thread(r, "android_" + counter++)
    }
}