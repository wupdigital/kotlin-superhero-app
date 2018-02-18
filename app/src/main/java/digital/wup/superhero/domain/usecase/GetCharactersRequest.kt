package digital.wup.superhero.domain.usecase


import digital.wup.superhero.data.model.Page
import digital.wup.superhero.domain.Request

data class GetCharactersRequest(var page: Page?) : Request