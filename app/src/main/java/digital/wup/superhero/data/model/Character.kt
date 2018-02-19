package digital.wup.superhero.data.model

import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

data class Character(
        @SerializedName("id")
        var id: Long,
        @SerializedName("name")
        var name: String,
        @SerializedName("description")
        var description: String,
        @SerializedName("thumbnail")
        var thumbnail: Image?
)
