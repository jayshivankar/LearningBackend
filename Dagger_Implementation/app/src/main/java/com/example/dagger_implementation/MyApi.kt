package com.example.dagger_implementation

interface MyApi {
    @GET("test")
    suspend fun get(): Response<ResponseBody>
}