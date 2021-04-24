package com.tanxf.hiltmvvm.data.api

import com.tanxf.hiltmvvm.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

}

