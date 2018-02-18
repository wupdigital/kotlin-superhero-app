package digital.wup.superhero.data


import digital.wup.superhero.data.model.Character
import digital.wup.superhero.data.model.CharacterDataWrapper
import digital.wup.superhero.data.model.Error
import digital.wup.superhero.data.model.Page
import digital.wup.superhero.data.network.CharacterService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersRemoteDataStore(okHttpClient: OkHttpClient) : CharactersDataStore {
    private val service: CharacterService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        service = retrofit.create(CharacterService::class.java)
    }

    override fun loadCharacters(page: Page, callback: CharactersDataStore.LoadCharactersCallback) {
        service.loadCharacters(page.limit, page.offset).enqueue(object : Callback<CharacterDataWrapper> {
            override fun onResponse(call: Call<CharacterDataWrapper>, response: Response<CharacterDataWrapper>) {
                if (response.body() != null)
                    callback.onSuccess(response.body()!!.data.results)
                else
                    callback.onError(Error("error", "onResponse"))
            }

            override fun onFailure(call: Call<CharacterDataWrapper>, t: Throwable) {
                callback.onError(Error("error", "onFailure"))
            }
        })
    }

    override fun loadCharacter(id: String, callback: CharactersDataStore.LoadCharactersCallback) {
        service.loadCharacter(id).enqueue(object : Callback<CharacterDataWrapper> {
            override fun onResponse(call: Call<CharacterDataWrapper>, response: Response<CharacterDataWrapper>) {
                if (response.body() != null)
                    callback.onSuccess(response.body()!!.data.results)
                else
                    callback.onError(Error("error", "onResponse"))
            }

            override fun onFailure(call: Call<CharacterDataWrapper>, t: Throwable) {
                callback.onError(Error("error", "onFailure"))
            }
        })
    }

    override fun saveCharacters(characters: Array<Character>, callback: CharactersDataStore.SaveCharactersCallback) {
        throw UnsupportedOperationException("Method not implemented!")
    }
}
