package uz.hamroev.bardambolnew.pdf

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClientPdf {

    val BASE_URL = "https://drive.google.com/"


    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object

    val service = retrofit.create(ApiServisPdf::class.java)
}