package com.tanxf.hiltmvvm.data.api

import com.tanxf.hiltmvvm.data.model.User
import retrofit2.Response

interface ApiHelper {

    suspend fun getUsers(): Response<List<User>>

}