package digital.wup.superhero.data


import digital.wup.superhero.data.model.Character
import digital.wup.superhero.data.model.Error
import digital.wup.superhero.data.model.Page

interface CharactersDataStore {
    fun loadCharacters(page: Page, callback: LoadCharactersCallback)

    fun loadCharacter(id: String, callback: LoadCharactersCallback)

    fun saveCharacters(characters: Array<Character>, callback: SaveCharactersCallback)

    interface LoadCharactersCallback {
        fun onSuccess(characters: Array<Character>)

        fun onError(error: Error)
    }

    interface SaveCharactersCallback {
        fun onSuccess()

        fun onError(error: Error)
    }
}
