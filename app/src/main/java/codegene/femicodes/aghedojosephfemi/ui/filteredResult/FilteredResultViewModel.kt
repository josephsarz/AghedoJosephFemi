package codegene.femicodes.aghedojosephfemi.ui.filteredResult

import androidx.lifecycle.ViewModel
import androidx.sqlite.db.SimpleSQLiteQuery
import codegene.femicodes.aghedojosephfemi.local.entity.FilterEntity
import codegene.femicodes.aghedojosephfemi.repository.FilterRepository
import timber.log.Timber
import javax.inject.Inject


class FilteredResultViewModel @Inject constructor(private val repository: FilterRepository) :
    ViewModel() {
    var filter: FilterEntity? = null

    val filteredResult by lazy {
        repository.observeFilteredResult(getQuery())
    }

    private fun getQuery(): SimpleSQLiteQuery {

        var containsCondition = false

        var queryString = "SELECT * FROM car_owners"
        var args: MutableList<String> = mutableListOf()

        if (filter?.gender != null) {
            queryString += " WHERE gender LIKE ?"
            args.add(filter?.gender!!)
            containsCondition = true
        }

        if (filter?.startYear != null) {

            if (containsCondition) {
                queryString += " AND"
            } else {
                queryString += " WHERE"
                containsCondition = true
            }
            queryString += " carModelYear BETWEEN ? AND ?"
            args.add(filter?.startYear.toString())
            args.add(filter?.endYear.toString())
        }

        if (!filter?.colors.isNullOrEmpty()) {

            for ((index, color) in filter?.colors!!.withIndex()) {

                if (index == 0) {
                    queryString += " AND"
                } else {
                    queryString += " OR"
                }

                queryString += " carColor LIKE ?"
                args.add(color!!)
            }
        }

        if (!filter?.countries.isNullOrEmpty()) {

            for ((index, country) in filter?.countries!!.withIndex()) {

                if (index == 0) {
                    queryString += " AND"
                } else {
                    queryString += " OR"
                }
                queryString += " country LIKE ?"
                args.add(country!!)
            }
        }
        Timber.e(queryString)
        return SimpleSQLiteQuery(queryString, args.toTypedArray())
    }

}