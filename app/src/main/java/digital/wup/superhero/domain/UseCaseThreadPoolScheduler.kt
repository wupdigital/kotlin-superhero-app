package digital.wup.superhero.domain


import android.os.Handler
import android.os.Looper

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

import digital.wup.superhero.data.model.Error

class UseCaseThreadPoolScheduler : UseCaseScheduler {

    private val mHandler = Handler(Looper.getMainLooper())

    internal var mThreadPoolExecutor: ThreadPoolExecutor

    init {
        mThreadPoolExecutor = ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT.toLong(),
                TimeUnit.SECONDS, ArrayBlockingQueue(POOL_SIZE))
    }

    override fun execute(runnable: Runnable) {
        mThreadPoolExecutor.execute(runnable)
    }

    override fun <Rs : Response> notifyResponse(response: Rs, callback: UseCaseCallback<Rs>) {
        mHandler.post { callback.onSuccess(response) }
    }

    override fun <Rs : Response> onError(error: Error, callback: UseCaseCallback<Rs>) {
        mHandler.post { callback.onError(error) }
    }

    companion object {

        val POOL_SIZE = 2

        val MAX_POOL_SIZE = 4

        val TIMEOUT = 30
    }
}
