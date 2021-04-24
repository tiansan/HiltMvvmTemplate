package com.tanxf.hiltmvvm.data.repository

import com.tanxf.hiltmvvm.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getUsers() =  apiHelper.getUsers()

}