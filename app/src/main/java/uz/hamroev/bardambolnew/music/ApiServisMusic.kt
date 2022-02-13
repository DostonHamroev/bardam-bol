package uz.hamroev.bardambolnew.music

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiServisMusic {


    @GET
    fun getMusic(@Url music_url: String): Call<ResponseBody>
}