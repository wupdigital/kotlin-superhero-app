package digital.wup.superhero.presentation.ui.details


import digital.wup.superhero.data.model.Error
import digital.wup.superhero.domain.UseCaseCallback
import digital.wup.superhero.domain.UseCaseHandler
import digital.wup.superhero.domain.usecase.GetCharacterRequest
import digital.wup.superhero.domain.usecase.GetCharacterResponse
import digital.wup.superhero.domain.usecase.GetCharacterUseCase

class DetailsPresenterImpl(private val useCase: GetCharacterUseCase, private val handler: UseCaseHandler) : DetailsContract.DetailsPresenter {
    private var view: DetailsContract.DetailsView? = null

    override fun takeView(view: DetailsContract.DetailsView) {
        this.view = view
    }

    override fun loadCharacter(id: String) {
        val request = GetCharacterRequest(id)

        handler.execute(useCase, request, object : UseCaseCallback<GetCharacterResponse> {
            override fun onSuccess(response: GetCharacterResponse) {
                view!!.showCharacter(response.character!!)
            }

            override fun onError(error: Error) {
                view!!.showLoadingCharacterError(error.message!!)
            }
        })
    }
}
