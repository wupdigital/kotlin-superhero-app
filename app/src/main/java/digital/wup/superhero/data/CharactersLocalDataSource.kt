package digital.wup.superhero.data


import android.arch.persistence.room.Room
import android.content.Context
import android.os.AsyncTask

import digital.wup.superhero.data.model.Character
import digital.wup.superhero.data.model.CharacterDataBase
import digital.wup.superhero.data.model.Error
import digital.wup.superhero.data.model.Page
import timber.log.Timber

class CharactersLocalDataSource(context: Context) : CharactersDataStore {
    private val dataBase: CharacterDataBase = Room.databaseBuilder(context.applicationContext, CharacterDataBase::class.java, "superhero-db").build()

    override fun loadCharacters(page: Page, callback: CharactersDataStore.LoadCharactersCallback) {
        Timber.d("CharactersLocalDataSource - loadCharacters")
        val characters = dataBase.characterDao().loadCharacters()
        if (characters != null && characters.isNotEmpty()) {
            callback.onSuccess(characters)
        } else {
            callback.onError(Error("error", "loadCharacters"))
        }
    }

    override fun loadCharacter(id: String, callback: CharactersDataStore.LoadCharactersCallback) {
        val character = dataBase.characterDao().loadCharacter(id)
        if (character != null)
            callback.onSuccess(arrayOf(character))
        else
            callback.onError(Error(Error.EMPTY, "Room retrieved nothing from database"))

    }

    override fun saveCharacters(characters: Array<Character>, callback: CharactersDataStore.SaveCharactersCallback) {
        Timber.d("CharactersLocalDataSource - saveCharacters")
        AsyncTask.execute {
            val success = dataBase.characterDao().saveCharacters(characters)
            if (success != null && success.size > 0)
                callback.onSuccess()
            else
                callback.onError(Error(Error.EMPTY, "Saving characters has failed"))
        }
    }
}
