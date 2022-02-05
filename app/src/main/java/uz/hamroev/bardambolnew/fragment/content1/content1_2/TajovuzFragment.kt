package uz.hamroev.bardambolnew.fragment.content1.content1_2

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.chrisbanes.photoview.PhotoView
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.hamroev.bardambolnew.adapter.ImageAdapter
import uz.hamroev.bardambolnew.cache.Cache
import uz.hamroev.bardambolnew.cache.Cache.path
import uz.hamroev.bardambolnew.databinding.FragmentTajovuzBinding
import uz.hamroev.bardambolnew.image.ApiClientImage
import uz.hamroev.bardambolnew.model.Image
import java.io.*


class TajovuzFragment : Fragment() {

    lateinit var binding: FragmentTajovuzBinding
    private val TAG = "TajovuzFragment"
    lateinit var imageAdapter: ImageAdapter
    lateinit var list: ArrayList<Image>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTajovuzBinding.inflate(layoutInflater, container, false)


        checkLanguage()
        checkDownload()

        imageAdapter = ImageAdapter(
            binding.root.context,
            list,
            object : ImageAdapter.OnMyImageDownloadClickListener {
                override fun onDownload(
                    image: Image,
                    position: Int,
                    downloadImage: View,
                    progress: View,
                    cardDownloadPunkt: View,
                    imageZoom: PhotoView
                ) {
                    isDownload(list[position].imageName, imageZoom, downloadImage, progress, position)
                }
            })
        binding.rvImage.adapter = imageAdapter

        return binding.root
    }

    private fun checkDownload() {

    }

    private fun isDownload(
        imageName: String,
        imageZoom: PhotoView,
        downloadImage: View,
        progress: View,
        position: Int
    ) {

        downloadImage.visibility = View.GONE
        progress.visibility = View.VISIBLE

        val service = ApiClientImage().service
        service.getImage(list[position].image_Url).enqueue(object : Callback<ResponseBody> {
            var imageview = imageZoom
            val context = binding.root.context
            val path = "${context.filesDir.absolutePath}/${imageName}.jpeg"

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                Log.d(TAG, "onResponse: $path")
                progress.visibility = View.GONE
                imageZoom.visibility = View.VISIBLE
                imageZoom.setImageURI(Uri.parse(path))

                if (Cache.path != "") {
                    imageview.setImageURI(Uri.parse(path))
                } else Cache.path = path
                response?.let { writeResponseBody(it.body(), path) }
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.d(TAG, "onFailure: ")
                Toast.makeText(context, "Xato", Toast.LENGTH_SHORT).show()

            }

        })
    }
    fun writeResponseBody(body: ResponseBody, path: String?): Boolean {
        return try {
            val file = File(path)
            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null
            try {
                val fileReader = ByteArray(4096)
                //long fileSize = body.contentLength();
                //long fileSizeDownloaded = 0;
                inputStream = body.byteStream()
                outputStream = FileOutputStream(file)
                while (true) {
                    val read: Int = inputStream.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream.write(fileReader, 0, read)
                    //fileSizeDownloaded += read;
                }
                outputStream.flush()
                true
            } catch (e: IOException) {
                false
            } finally {
                if (inputStream != null) {
                    inputStream.close()
                }
                if (outputStream != null) {
                    outputStream.close()
                }
            }
        } catch (e: IOException) {
            false
        }
    }


    private fun checkLanguage() {
        Log.d(TAG, "checkLanguage: ")
        when (Cache.til) {
            "uz" -> {
                loadUzData()
            }
            "krill" -> {
                loadKrillData()
            }
            "ru" -> {
                loadRuData()
            }
        }
    }

    private fun loadRuData() {
        Log.d(TAG, "loadRuData: ")
        list = ArrayList()
        list.clear()
        list.add(Image("max/2000/1*pelmmYTGS2LAim1Xc7iDSw.png", "agressiyadaBrinchiYordam1RU", ""))
        list.add(Image("max/1400/1*jHsOcJkltJ2PSPEVswfgyg.png", "agressiyadaBrinchiYordam2RU", ""))
        list.add(Image("max/2000/1*pelmmYTGS2LAim1Xc7iDSw.png", "agressiyadaBrinchiYordam1RU", ""))
    }

    private fun loadKrillData() {
        Log.d(TAG, "loadKrillData: ")
        list = ArrayList()
        list.clear()
        list.add(Image("max/2000/1*pelmmYTGS2LAim1Xc7iDSw.png", "agressiyadaBrinchiYordam1Krill", ""))
        list.add(Image("max/1400/1*jHsOcJkltJ2PSPEVswfgyg.png", "agressiyadaBrinchiYordam2Krill", ""))

    }

    private fun loadUzData() {
        Log.d(TAG, "loadUzData: ")
        list = ArrayList()
        list.clear()
        list.add(Image("max/2000/1*pelmmYTGS2LAim1Xc7iDSw.png", "agressiyadaBrinchiYordam1UZ", ""))
        list.add(Image("max/1400/1*jHsOcJkltJ2PSPEVswfgyg.png", "agressiyadaBrinchiYordam2UZ", ""))

    }

}