package digital.wup.superhero.data.model


import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

data class Image(
        @SerializedName("path")
        var path: String,
        @SerializedName("extension")
        var extension: String
)
