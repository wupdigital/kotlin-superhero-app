package digital.wup.superhero.domain.usecase


import digital.wup.superhero.domain.Request

data class GetCharacterRequest(var characterId: String?) : Request
