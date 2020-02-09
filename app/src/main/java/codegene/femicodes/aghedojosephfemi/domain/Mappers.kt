package codegene.femicodes.aghedojosephfemi.domain

import codegene.femicodes.aghedojosephfemi.local.entity.FilterEntity
import codegene.femicodes.aghedojosephfemi.remote.FilterResponse


fun List<FilterResponse>.toEntity(): List<FilterEntity> = map {
    FilterEntity(
        gender = it.gender,
        startYear = it.startYear,
        id = it.id,
        countries = it.countries,
        endYear = it.endYear,
        colors = it.colors
    )
}

fun FilterEntity.mapEntity(filterId: Int): FilterEntity {
    return FilterEntity(
        gender = gender,
        startYear = startYear,
        id = filterId,
        countries = countries,
        endYear = endYear,
        colors = colors
    )
}