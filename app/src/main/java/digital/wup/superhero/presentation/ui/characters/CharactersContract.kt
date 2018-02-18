package digital.wup.superhero.presentation.ui.characters


import android.os.Bundle

import digital.wup.superhero.data.model.Character
import digital.wup.superhero.presentation.Presenter
import digital.wup.superhero.presentation.View

interface CharactersContract {
    interface CharactersPresenter : Presenter {
        fun takeView(view: CharactersView)

        fun characters(): Array<Character>

        fun charactersCount(): Int

        fun loadCharacters()

        fun loadMoreCharacters()
    }

    interface CharactersView : View {
        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showMoreLoadingIndicator()

        fun hideMoreLoadingIndicator()

        fun showCharacters(characters: Array<Character>)

        fun showLoadingCharactersError(message: String)

        fun showNoCharacters()

        fun navigateToDetails(bundle: Bundle)
    }
}
