package digital.wup.superhero.data.model


import com.google.gson.annotations.SerializedName

data class CharacterDataContainer(
        @SerializedName("offset")
        var offset: Int,
        @SerializedName("limit")
        var limit: Int,
        @SerializedName("total")
        var total: Int,
        @SerializedName("count")
        var count: Int,
        @SerializedName("results")
        var results: Array<Character>
)
