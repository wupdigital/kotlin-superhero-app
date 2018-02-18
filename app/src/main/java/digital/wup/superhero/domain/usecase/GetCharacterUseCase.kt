package digital.wup.superhero.domain.usecase


import digital.wup.superhero.data.CharactersDataStore
import digital.wup.superhero.data.model.Character
import digital.wup.superhero.data.model.Error
import digital.wup.superhero.domain.UseCase

class GetCharacterUseCase(private val charactersDataStore: CharactersDataStore) : UseCase<GetCharacterRequest, GetCharacterResponse>() {

    override fun executeUseCase(request: GetCharacterRequest?) {
        charactersDataStore.loadCharacter(request!!.characterId!!, object : CharactersDataStore.LoadCharactersCallback {
            override fun onSuccess(characters: Array<Character>) {
                val response = GetCharacterResponse(characters[0])
                useCaseCallback!!.onSuccess(response)
            }

            override fun onError(error: Error) {
                useCaseCallback!!.onError(error)
            }
        })
    }
}
