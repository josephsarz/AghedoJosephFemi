package codegene.femicodes.aghedojosephfemi.repository

import androidx.lifecycle.LiveData
import androidx.sqlite.db.SupportSQLiteQuery
import codegene.femicodes.aghedojosephfemi.domain.mapEntity
import codegene.femicodes.aghedojosephfemi.domain.resultLiveData
import codegene.femicodes.aghedojosephfemi.domain.toEntity
import codegene.femicodes.aghedojosephfemi.local.dao.CarOwnerDao
import codegene.femicodes.aghedojosephfemi.local.dao.FilterDao
import codegene.femicodes.aghedojosephfemi.local.entity.CarOwnerEntity
import codegene.femicodes.aghedojosephfemi.local.entity.FilterEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilterRepository @Inject constructor(
    private val filterDao: FilterDao,
    private val carOwnerDao: CarOwnerDao,
    private val remote: FilterRemoteDataSource
) {


    fun observeFilter() = resultLiveData(
        databaseQuery = { filterDao.getData() },
        networkCall = { remote.fetchFilters() },
        saveCallResult = { filterDao.insertAll(it.toEntity()) }
    )

    fun observeFilteredResult(query: SupportSQLiteQuery): LiveData<List<CarOwnerEntity>> {
        return carOwnerDao.getFilteredResult(query)
    }

    suspend fun addFilter(filter: FilterEntity) {
        GlobalScope.launch(Dispatchers.Main) {
            val index = async(Dispatchers.IO) { filterDao.getNextIndex() }
            val f = filter.mapEntity(index.await())
            filterDao.insert(f)
        }
    }

}