package codegene.femicodes.aghedojosephfemi.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import codegene.femicodes.aghedojosephfemi.local.entity.CarOwnerEntity

@Dao
interface CarOwnerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(owners: List<CarOwnerEntity>)

    @Query("SELECT * FROM car_owners")
    fun getData(): LiveData<CarOwnerEntity>

//    @Query("SELECT * FROM car_owners WHERE gender LIKE :gender ")
//    fun getFilteredResult(gender: String): LiveData<List<CarOwnerEntity>>

//    @RawQuery
//    fun getFilteredResult(query : String): LiveData<List<CarOwnerEntity>>

    @RawQuery(observedEntities = [CarOwnerEntity::class])
    fun getFilteredResult(query: SupportSQLiteQuery): LiveData<List<CarOwnerEntity>>

}
