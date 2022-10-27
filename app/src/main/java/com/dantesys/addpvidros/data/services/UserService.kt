package com.dantesys.addpvidros.data.services

import com.dantesys.addpvidros.data.dtos.LoginDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
    @Headers("Content-Type: application/json")
    @POST("users/login")
    fun login(@Body loginDTO: LoginDTO): Call<String>
}