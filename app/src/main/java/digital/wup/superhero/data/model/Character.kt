package digital.wup.superhero.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

import com.google.gson.annotations.SerializedName

@Entity
data class Character(
        @PrimaryKey
        @SerializedName("id")
        var id: Int,
        @ColumnInfo(name = "name")
        @SerializedName("name")
        var name: String,
        @ColumnInfo(name = "description")
        @SerializedName("description")
        var description: String,
        @Embedded
        @SerializedName("thumbnail")
        var thumbnail: Image
)
