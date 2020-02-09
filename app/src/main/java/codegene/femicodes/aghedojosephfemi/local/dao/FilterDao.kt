package codegene.femicodes.aghedojosephfemi.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import codegene.femicodes.aghedojosephfemi.local.entity.FilterEntity

@Dao
interface FilterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(filters: List<FilterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(filter: FilterEntity): Long

    @Query("SELECT * FROM filters")
    fun getData(): LiveData<List<FilterEntity>>

    @Query("SELECT MAX(id) + 1 FROM filters")
    suspend fun getNextIndex(): Int

}
