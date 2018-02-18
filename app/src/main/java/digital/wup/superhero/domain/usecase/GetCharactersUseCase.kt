package digital.wup.superhero.domain.usecase


import digital.wup.superhero.data.CharactersDataStore
import digital.wup.superhero.data.model.Character
import digital.wup.superhero.data.model.Error
import digital.wup.superhero.domain.UseCase

class GetCharactersUseCase(private val charactersDataStore: CharactersDataStore) : UseCase<GetCharactersRequest, GetCharactersResponse>() {

    override fun executeUseCase(request: GetCharactersRequest?) {
        charactersDataStore.loadCharacters(request?.page!!, object : CharactersDataStore.LoadCharactersCallback {
            override fun onSuccess(characters: Array<Character>) {
                val response = GetCharactersResponse(null)
                response.characters = characters

                useCaseCallback?.onSuccess(response)
            }

            override fun onError(error: Error) {
                useCaseCallback?.onError(error)
            }
        })
    }
}
