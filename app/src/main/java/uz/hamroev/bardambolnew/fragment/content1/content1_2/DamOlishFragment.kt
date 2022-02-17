package uz.hamroev.bardambolnew.fragment.content1.content1_2

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.hamroev.bardambolnew.R
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
    var mediaPlayer: MediaPlayer? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDamOlishBinding.inflate(layoutInflater, container, false)



        checkIsDownload()
        binding.downloadBtn.setOnClickListener {
            loadMusic()
        }


        return binding.root
    }

    private fun loadMusic() {
        Cache.musicPath1 = ""
        binding.downloadBtn.visibility = View.GONE
        binding.loadingBtn.visibility = View.VISIBLE

        var music_url = "https://drive.google.com/u/0/uc?id=1PItSfdX0aVCZ56PfsM8XcWBEENy7apoz&export=download"
        val service = ApiClientMusic().service

        service.getMusic(music_url).enqueue(object : Callback<ResponseBody> {
            val context = binding.root.context
            val path = "${context.filesDir.absolutePath}/relaksiya.mp3"

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                Log.d("Audio", "onResponse: ")
                Cache.musicPath1 = path
                response?.let { writeResponseBody(it.body(), path) }
                checkIsDownload()
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.d("Audio", "onFailure: ")
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

    private fun checkIsDownload() {
        Log.d("Audio", "checkIsDownload: ")
        if (Cache.musicPath1 == "") {
            binding.cardPlay.visibility = View.GONE
        } else {
            binding.cardPlay.visibility = View.VISIBLE
            binding.downloadBtn.visibility = View.GONE
            binding.loadingBtn.visibility = View.GONE
            binding.musicIcon.visibility = View.VISIBLE
            playMusic()
        }
    }

    private fun playMusic() {

        Log.d("Audio", "playMusic: ${Cache.musicPath1}")

        try {
            mediaPlayer = MediaPlayer.create(requireContext(), Uri.parse(Cache.musicPath1))

            binding.playBtn.setOnClickListener {
                if (mediaPlayer!!.isPlaying){
                    mediaPlayer!!.pause()
                    binding.playBtn.setImageResource(R.drawable.ic_play)
                } else {
                    mediaPlayer!!.start()
                    binding.playBtn.setImageResource(R.drawable.ic_pause)
                }
            }

        } catch (e: Exception){
            e.printStackTrace()
        }
    }
    private fun releaseMP(){
        if (mediaPlayer != null){
            try {
                mediaPlayer?.release()
                mediaPlayer= null
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMP()
    }

    override fun onPause() {
        super.onPause()
        releaseMP()
    }

}