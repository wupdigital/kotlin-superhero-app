package digital.wup.superhero.data


import android.app.Application
import digital.wup.superhero.SuperheroApp
import digital.wup.superhero.data.model.*
import digital.wup.superhero.data.model.local.CharacterDto
import digital.wup.superhero.data.model.local.CharacterDto_
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import io.objectbox.query.QueryBuilder

class CharactersLocalDataSource(application: Application) : CharactersDataStore {
    private val dataBase: Box<CharacterDto> = (application as SuperheroApp).boxStore.boxFor<CharacterDto>()

    override fun loadCharacters(page: Page, callback: CharactersDataStore.LoadCharactersCallback) {
        val queryBuilder: QueryBuilder<CharacterDto> = dataBase.query()
        val charactersDto = queryBuilder.build().find()
        var characters: MutableList<Character> = mutableListOf()
        charactersDto.mapTo(characters) { it.to() }
        if (characters != null && characters.isNotEmpty()) {
            callback.onSuccess(characters.toTypedArray())
        } else {
            callback.onError(Error("error", "loadCharacters"))
        }
    }

    override fun loadCharacter(id: String, callback: CharactersDataStore.LoadCharactersCallback) {
        val queryBuilder: QueryBuilder<CharacterDto> = dataBase.query()
        val character = queryBuilder.equal(CharacterDto_.characterId, id).build().findFirst()
        if (character != null)
            callback.onSuccess(arrayOf(character.to()))
        else
            callback.onError(Error(Error.EMPTY, "Room retrieved nothing from database"))

    }

    override fun saveCharacters(characters: Array<Character>, callback: CharactersDataStore.SaveCharactersCallback) {
        try {
            val charactersDto: MutableList<CharacterDto> = mutableListOf()
            characters.mapTo(charactersDto) { it.toDto() }
            dataBase.put(charactersDto)
            callback.onSuccess()
        } catch (e: Exception) {
            callback.onError(Error(Error.EMPTY, "Saving characters has failed"))
        }
    }
}
