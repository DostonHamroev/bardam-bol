package uz.hamroev.bardambolnew.image

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceImage {
    @GET("{img_Url}")
    fun getImage(@Path("img_Url") img_Url: String): Call<ResponseBody>
}