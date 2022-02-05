package uz.hamroev.bardambolnew.image

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClientImage {
    val BASE_URL = "https://miro.medium.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object

    val service = retrofit.create(ApiServiceImage::class.java)
}