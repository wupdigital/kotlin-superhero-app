package digital.wup.superhero.domain

import digital.wup.superhero.data.model.Error

interface UseCaseScheduler {
    fun execute(runnable: Runnable)

    fun <Rs : Response> notifyResponse(response: Rs, callback: UseCaseCallback<Rs>)

    fun <Rs : Response> onError(error: Error, callback: UseCaseCallback<Rs>)
}
