package codegene.femicodes.aghedojosephfemi.repository

import codegene.femicodes.aghedojosephfemi.domain.BaseDataSource
import codegene.femicodes.aghedojosephfemi.remote.AppService
import javax.inject.Inject

class FilterRemoteDataSource @Inject constructor(private val service: AppService) :
    BaseDataSource() {

    suspend fun fetchFilters() = getResult { service.getFilters() }

}