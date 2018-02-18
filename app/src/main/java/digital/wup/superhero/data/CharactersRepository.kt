package digital.wup.superhero.data


import digital.wup.superhero.data.model.Character
import digital.wup.superhero.data.model.Error
import digital.wup.superhero.data.model.Page

class CharactersRepository(private val remoteDataStore: CharactersRemoteDataStore, private val localDataSource: CharactersLocalDataSource) : CharactersDataStore {

    override fun loadCharacters(page: Page, callback: CharactersDataStore.LoadCharactersCallback) {
        localDataSource.loadCharacters(page, object : CharactersDataStore.LoadCharactersCallback {
            override fun onSuccess(characters: Array<Character>) {
                callback.onSuccess(characters)
            }

            override fun onError(error: Error) {
                remoteDataStore.loadCharacters(page, object : CharactersDataStore.LoadCharactersCallback {
                    override fun onSuccess(characters: Array<Character>) {
                        localDataSource.saveCharacters(characters, object : CharactersDataStore.SaveCharactersCallback {
                            override fun onSuccess() {
                                callback.onSuccess(characters)
                            }

                            override fun onError(error: Error) {
                                callback.onError(error)
                            }
                        })
                    }

                    override fun onError(error: Error) {
                        callback.onError(error)
                    }
                })
            }
        })
    }

    override fun loadCharacter(id: String, callback: CharactersDataStore.LoadCharactersCallback) {
        localDataSource.loadCharacter(id, object : CharactersDataStore.LoadCharactersCallback {
            override fun onSuccess(characters: Array<Character>) {
                callback.onSuccess(characters)
            }

            override fun onError(error: Error) {
                remoteDataStore.loadCharacter(id, object : CharactersDataStore.LoadCharactersCallback {
                    override fun onSuccess(characters: Array<Character>) {
                        localDataSource.saveCharacters(characters, object : CharactersDataStore.SaveCharactersCallback {
                            override fun onSuccess() {
                                callback.onSuccess(characters)
                            }

                            override fun onError(error: Error) {
                                callback.onError(error)
                            }
                        })

                    }

                    override fun onError(error: Error) {
                        callback.onError(error)
                    }
                })
            }
        })
    }

    override fun saveCharacters(characters: Array<Character>, callback: CharactersDataStore.SaveCharactersCallback) {
        throw UnsupportedOperationException("Method not implemented!")
    }
}
