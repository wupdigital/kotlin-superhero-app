package digital.wup.superhero.domain.usecase


import digital.wup.superhero.data.model.Character
import digital.wup.superhero.domain.Response

data class GetCharacterResponse(var character: Character?) : Response