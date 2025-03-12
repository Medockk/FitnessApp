package com.example.fitnessapp.feature_app.data.data_source.services

import com.example.fitnessapp.feature_app.data.model.UserRetrofitDataImpl
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitApi {

    @GET("v2/pet/{petId}")
    suspend fun getUserById(
        @Path("petId") petId: Int
    ) : Response<UserRetrofitDataImpl>

    @POST("v2/pet")
    suspend fun postUser(
        @Body userRetrofitDataImpl: UserRetrofitDataImpl
    ) : Response<UserRetrofitDataImpl>
}