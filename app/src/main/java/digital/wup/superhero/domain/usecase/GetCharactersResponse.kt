package digital.wup.superhero.domain.usecase


import digital.wup.superhero.domain.Response
import digital.wup.superhero.data.model.Character

data class GetCharactersResponse(var characters: Array<Character>?) : Response
