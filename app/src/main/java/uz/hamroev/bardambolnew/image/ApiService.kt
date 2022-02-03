package uz.hamroev.bardambolnew.image

import android.media.Image
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("max/1400/1*oqyuIpxK_rxsjI5j_nlw2Q.jpeg")
    fun getImage():Call<ResponseBody>
}