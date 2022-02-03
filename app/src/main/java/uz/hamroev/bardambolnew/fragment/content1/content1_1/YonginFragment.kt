package uz.hamroev.bardambolnew.fragment.content1.content1_1

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
import uz.hamroev.bardambolnew.databinding.FragmentYonginBinding
import uz.hamroev.bardambolnew.image.ApiClient
import uz.hamroev.bardambolnew.model.Image
import uz.hamroev.bardambolnew.room.FileDatabase
import uz.hamroev.bardambolnew.room.FileEntity
import xyz.teamgravity.checkinternet.CheckInternet
import java.io.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [YonginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class YonginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentYonginBinding
    lateinit var list: ArrayList<Image>
    lateinit var imageAdapter: ImageAdapter
    lateinit var fileDatabase: FileDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentYonginBinding.inflate(layoutInflater, container, false)

        fileDatabase = FileDatabase.getInstance(binding.root.context)
        checkLanguage()
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
                    downloadImage.visibility = View.GONE
                    progress.visibility = View.VISIBLE

                    isDownload(list[position].imageName, imageZoom)

                }
            })
        binding.rvImage.adapter = imageAdapter

        return binding.root
    }



    private fun checkLanguage() {
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
        list = ArrayList()
        list.clear()
        list.add(Image("a", ""))
        list.add(Image("b", ""))
    }

    private fun loadKrillData() {
        list = ArrayList()
        list.clear()
        list.add(Image("", ""))
        list.add(Image("", ""))
    }

    private fun loadUzData() {
        list = ArrayList()
        list.clear()
        list.add(Image("", ""))
        list.add(Image("", ""))
    }

    private fun isDownload(imageName: String, imageZoom: PhotoView) {
        var imageList = fileDatabase.fileDao().getSearchFileName(imageName)
        for (fileEntity in imageList) {
            if (fileEntity.file_name == imageName) {
                imageZoom.visibility = View.VISIBLE
                imageZoom.setImageURI(Uri.parse(fileEntity.file_path))
            } else {
                CheckInternet().check { isHaveInternet ->
                    if (isHaveInternet) {
                        val service = ApiClient().service
                        service.getImage().enqueue(object : Callback<ResponseBody> {
                            var imageview = imageZoom
                            val context = binding.root.context
                            val path = "${context.filesDir.absolutePath}/${imageName}.jpeg"
                            override fun onResponse(
                                call: Call<ResponseBody>?,
                                response: Response<ResponseBody>?
                            ) {
                                var fileEntity1 = FileEntity()
                                fileEntity1.is_download = "yes"
                                fileEntity1.file_name = imageName
                                fileEntity1.file_path = path
                                fileDatabase.fileDao().addFile(fileEntity1)
                                imageview.setImageURI(Uri.parse(path))
                                imageview.visibility = View.VISIBLE
                                Log.d("Rasmuchun", "onResponse: ")
//                                if (Cache.path != "") {
//                                    imageview.setImageURI(Uri.parse(path))
//                                } else Cache.path = path
                                response?.let { writeResponseBody(it.body(), path) }
                            }

                            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                                Log.d("Rasmuchun", "onFailure: ")
                                Toast.makeText(binding.root.context, "Xato", Toast.LENGTH_SHORT)
                                    .show()
                                //imageview.setImageURI(Uri.parse(path))
                            }

                        })
                    } else {
                        Toast.makeText(binding.root.context, "No Internet", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
            }
        }
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
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: IOException) {
            false
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment YonginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            YonginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}