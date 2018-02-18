package digital.wup.superhero.presentation.ui.characters


import digital.wup.superhero.data.model.Character
import digital.wup.superhero.data.model.Error
import digital.wup.superhero.data.model.Page
import digital.wup.superhero.domain.UseCaseCallback
import digital.wup.superhero.domain.UseCaseHandler
import digital.wup.superhero.domain.usecase.GetCharactersRequest
import digital.wup.superhero.domain.usecase.GetCharactersResponse
import digital.wup.superhero.domain.usecase.GetCharactersUseCase

class CharacatersPresenterImpl(private val useCase: GetCharactersUseCase, private val handler: UseCaseHandler) : CharactersContract.CharactersPresenter {
    private var view: CharactersContract.CharactersView? = null

    override fun takeView(view: CharactersContract.CharactersView) {
        this.view = view
    }

    override fun characters(): Array<Character> {
        return arrayOf()
    }

    override fun charactersCount(): Int {
        return 0
    }

    override fun loadCharacters() {
        val request = GetCharactersRequest(Page(100, 10))

        handler.execute(useCase, request, object : UseCaseCallback<GetCharactersResponse> {
            override fun onSuccess(response: GetCharactersResponse) {
                if (response.characters != null && response.characters!!.size > 0)
                    view!!.showCharacters(response.characters!!)
            }

            override fun onError(error: Error) {
                view!!.showNoCharacters()
            }
        })
    }

    override fun loadMoreCharacters() {

    }
}
