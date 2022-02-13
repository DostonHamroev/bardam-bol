package uz.hamroev.bardambolnew.fragment.content1.content1_2

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.hamroev.bardambolnew.cache.Cache
import uz.hamroev.bardambolnew.databinding.FragmentDamOlishBinding
import uz.hamroev.bardambolnew.music.ApiClientMusic
import java.io.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DamOlishFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DamOlishFragment : Fragment() {
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

    lateinit var binding: FragmentDamOlishBinding
    private val TAG = "DamOlishFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDamOlishBinding.inflate(layoutInflater, container, false)

        checkCache()

        binding.downloadBtn.setOnClickListener {
            checkMusic()
        }

        binding.musicBtn.setOnClickListener {
            checkMusic()
        }


        return binding.root
    }

    private fun checkCache() {
        if (Cache.musicPath1 == "") {
            Log.d(TAG, "checkCache: Load qil")
            binding.musicBtn.visibility = View.GONE
            binding.loadingProgress.visibility = View.GONE
            binding.downloadBtn.visibility = View.VISIBLE
        } else {
            Log.d(TAG, "checkCache: Open qil")
            binding.musicBtn.visibility = View.VISIBLE
            binding.downloadBtn.visibility = View.GONE
            binding.loadingProgress.visibility = View.GONE
        }
    }

    private fun checkMusic() {
        if (Cache.musicPath1 == "") loadMusic() else openMusic()
    }

    private fun openMusic() {
        binding.musicBtn.visibility = View.VISIBLE
        binding.loadingProgress.visibility = View.GONE
        binding.downloadBtn.visibility = View.GONE


//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        val file = File(Cache.musicPath1)
//        val fromFile = Uri.fromFile(file)
//        intent.data = fromFile
//        intent.setDataAndType(fromFile, "audio/*")
//        startActivity(intent)

//        val file = File(Cache.musicPath1)
//        val uri = Uri.fromFile(file)
//        val intent = Intent(Intent.ACTION_VIEW, uri)
//        intent.setDataAndType(uri, "audio/*")
//        startActivity(intent)

//        val it = Intent(Intent.ACTION_VIEW)
//        val uri = Uri.parse("${Cache.musicPath1}")
//        it.setDataAndType(uri, "audio/mp3")
//        startActivity(it)

        val mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(Cache.musicPath1)
        mediaPlayer.prepare()
        mediaPlayer.start()


    }

    private fun loadMusic() {

        when (Cache.til) {
            "uz" -> {
                downloadMusicUZ()
            }
            "krill" -> {
                downloadMusicKRILL()
            }
            "ru" -> {
                downloadMusicRU()
            }
        }
    }

    private fun downloadMusicRU() {

    }

    private fun downloadMusicKRILL() {

    }

    private fun downloadMusicUZ() {
        binding.downloadBtn.visibility = View.GONE
        binding.loadingProgress.visibility = View.VISIBLE
        val service = ApiClientMusic().service
        val uz_url_music = "u/0/uc?id=1PItSfdX0aVCZ56PfsM8XcWBEENy7apoz&export=download"
        val music_name = "Relaksiya"
        binding.musicName.text = music_name.toString().trim()

        service.getMusic(uz_url_music)
            .enqueue(object : Callback<ResponseBody> {
                val context = binding.root.context
                val path = "${context.filesDir.absolutePath}/${music_name}.mp3"

                override fun onResponse(
                    call: Call<ResponseBody>?,
                    response: Response<ResponseBody>?
                ) {
                    Log.d(TAG, "onResponse: ")
                    if (Cache.path != "") {
                        openMusic()
                    } else Cache.musicPath1 = path
                    response?.let { writeResponseBody(it.body(), path) }
                    openMusic()
                }

                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    Log.d(TAG, "onFailure: ")
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

                val fileSize = body.contentLength()
                var fileSizeDownloaded: Long = 0

                inputStream = body.byteStream()
                outputStream = FileOutputStream(file)
                while (true) {
                    val read: Int = inputStream.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream.write(fileReader, 0, read)
                    //fileSizeDownloaded += read;
                    fileSizeDownloaded += read.toLong()
                    Log.d(TAG, "file download: $fileSizeDownloaded of $fileSize")


                    /*
                    set progress

                    * */
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


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DamOlishFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DamOlishFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}