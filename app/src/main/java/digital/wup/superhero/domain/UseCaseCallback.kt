package digital.wup.superhero.domain

import digital.wup.superhero.data.model.Error

interface UseCaseCallback<Rs> {
    fun onSuccess(response: Rs)

    fun onError(error: Error)
}
