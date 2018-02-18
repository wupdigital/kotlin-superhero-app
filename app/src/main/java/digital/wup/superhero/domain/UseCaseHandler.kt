package digital.wup.superhero.domain

import digital.wup.superhero.data.model.Error

class UseCaseHandler(private val useCaseScheduler: UseCaseScheduler) {

    fun <Rq : Request, Rs : Response> execute(useCase: UseCase<Rq, Rs>, request: Rq, callback: UseCaseCallback<Rs>) {
        useCase.request = request
        useCase.useCaseCallback = object : UseCaseCallback<Rs> {
            override fun onSuccess(response: Rs) {
                notifyResponse(response, callback)
            }

            override fun onError(error: Error) {
                notifyError(error, callback)
            }
        }
        useCaseScheduler.execute(Runnable { useCase.run() })
    }

    fun <Rs : Response> notifyResponse(response: Rs, useCaseCallback: UseCaseCallback<Rs>) {
        useCaseScheduler.notifyResponse(response, useCaseCallback)
    }

    fun <Rs : Response> notifyError(error: Error, useCaseCallback: UseCaseCallback<Rs>) {
        useCaseScheduler.onError(error, useCaseCallback)
    }
}
