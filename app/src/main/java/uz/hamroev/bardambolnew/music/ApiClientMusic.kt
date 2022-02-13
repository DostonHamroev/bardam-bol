package uz.hamroev.bardambolnew.music

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClientMusic {

    val BASE_URL = "https://drive.google.com/"


    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object

    val service = retrofit.create(ApiServisMusic::class.java)
}