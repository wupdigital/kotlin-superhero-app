package digital.wup.superhero.presentation.ui.details


import digital.wup.superhero.data.model.Character
import digital.wup.superhero.presentation.Presenter
import digital.wup.superhero.presentation.View

interface DetailsContract {
    interface DetailsPresenter : Presenter {
        fun takeView(view: DetailsContract.DetailsView)

        fun loadCharacter(id: String)
    }

    interface DetailsView : View {
        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showCharacter(characters: Character)

        fun showLoadingCharacterError(message: String)

        fun showNoCharacter()
    }
}
