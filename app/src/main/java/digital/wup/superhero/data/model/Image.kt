package digital.wup.superhero.data.model


import android.arch.persistence.room.ColumnInfo

import com.google.gson.annotations.SerializedName

data class Image(
        @ColumnInfo(name = "path")
        @SerializedName("path")
        val path: String,
        @ColumnInfo(name = "extension")
        @SerializedName("extension")
        val extension: String
)
