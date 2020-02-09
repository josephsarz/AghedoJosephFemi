package codegene.femicodes.aghedojosephfemi.remote


import retrofit2.Response
import retrofit2.http.GET


interface AppService {

    @GET("assessment/filter.json")
    suspend fun getFilters(): Response<List<FilterResponse>>

}