package com.example.combinedrecycleview.network

import com.example.combinedrecycleview.model.JobsResponse
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    companion object {
        private const val GET_GetCOMBINED_JOBS = "apijs/v2/seeker/{seekerId}/jobs"
    }

    /**         Get Combined Jobs  API Call      **/
    @PUT(GET_GetCOMBINED_JOBS)
    fun onGetCombinedJobs(
        @Header("Authorization") token: String,
        @Path("seekerId") seekerId: String,
        @Body json: JsonObject,
    ): Observable<JobsResponse>
}