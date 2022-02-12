package uz.hamroev.bardambolnew.pdf

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiServisPdf {


    @GET
    fun getPdf(@Url pdf_url: String): Call<ResponseBody>
}