package digital.wup.superhero.data.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    fun loadCharacters(): Array<Character>

    @Query("SELECT * FROM character WHERE ID IN :id")
    fun loadCharacter(id: String): Character

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCharacters(characters: Array<Character>): Array<Long>
}
