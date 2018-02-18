package digital.wup.superhero.data.model


import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [(Character::class)], version = 1, exportSchema = false)
abstract class CharacterDataBase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
