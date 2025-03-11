package com.example.fitnessapp.feature_app.data.data_source.services

import com.example.fitnessapp.feature_app.domain.model.UserRetrofitData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("petstore.swagger.io/v2//pet/{petId}")
    suspend fun getUserById(
        @Query("petId") id: Int
    ) : Response<UserRetrofitData>


}