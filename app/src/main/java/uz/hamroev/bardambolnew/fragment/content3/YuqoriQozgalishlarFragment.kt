package uz.hamroev.bardambolnew.fragment.content3

import android.app.DownloadManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.pdfview.PDFView
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.hamroev.bardambolnew.adapter.PdfDownloadAdapter
import uz.hamroev.bardambolnew.adapter.VideoAdapter
import uz.hamroev.bardambolnew.cache.Cache
import uz.hamroev.bardambolnew.databinding.FragmentYuqoriQozgalishlarBinding
import uz.hamroev.bardambolnew.db.FileDatabase
import uz.hamroev.bardambolnew.db.FileEntity
import uz.hamroev.bardambolnew.model.PdfDownload
import uz.hamroev.bardambolnew.model.Video
import uz.hamroev.bardambolnew.pdf.ApiClientPdf
import java.io.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [YuqoriQozgalishlarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class YuqoriQozgalishlarFragment : Fragment() {
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

    lateinit var binding: FragmentYuqoriQozgalishlarBinding
    lateinit var list: ArrayList<PdfDownload>
    lateinit var pdfDownloadAdapter: PdfDownloadAdapter
    private val TAG = "AAAA"
    lateinit var fileDatabase: FileDatabase

    lateinit var listVideo: ArrayList<Video>
    lateinit var videoAdapter: VideoAdapter
    lateinit var downloadText: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYuqoriQozgalishlarBinding.inflate(layoutInflater)

        checkLanguage()

        fileDatabase = FileDatabase.getInstance(binding.root.context)
        val searchFileNameList = list[0].pdfName?.let { fileDatabase.fileDao().searchFileName(it) }

        if (searchFileNameList != null) {
            checkIsDownload(searchFileNameList)
        }

        return binding.root
    }

    private fun loadVideo() {

        videoAdapter = VideoAdapter(
            binding.root.context,
            listVideo,
            object : VideoAdapter.OnMyVideoClickListener {
                override fun onDownload(video: Video, position: Int, view: View) {
                    view.visibility = View.GONE
                    videoDownload(position)
                }

                private fun videoDownload(videoUrlPosition: Int) {
                    Toast.makeText(binding.root.context, "${downloadText}", Toast.LENGTH_SHORT)
                        .show()
                    Observable.fromCallable {
                        var downloadManger: DownloadManager
                        object : YouTubeExtractor(binding.root.context) {
                            override fun onExtractionComplete(
                                ytFiles: SparseArray<YtFile>?,
                                videoMeta: VideoMeta?
                            ) {
                                if (ytFiles != null) {
                                    var itag = 22
                                    var newLink = ytFiles.get(itag).url
                                    var title = "Video ${listVideo[videoUrlPosition].titleVideo}"
                                    var request = DownloadManager.Request(Uri.parse(newLink))
                                    request.setTitle(title)
                                    request.setDestinationInExternalPublicDir(
                                        Environment.DIRECTORY_DOWNLOADS,
                                        "/Bardam/${title}.mp4"
                                    )
                                    downloadManger =
                                        activity?.getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
                                    request.allowScanningByMediaScanner()
                                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE and DownloadManager.Request.NETWORK_WIFI)
                                    downloadManger.enqueue(request)
                                }
                            }
                        }.extract("https://www.youtube.com/watch?v=${listVideo[videoUrlPosition].videoId}")

                        Log.d("AAAA", "videoDownload: ${listVideo[videoUrlPosition].videoId}")

                    }.subscribe()
                }

            })
        binding.rvVideo.adapter = videoAdapter

    }

    private fun checkIsDownload(list: List<FileEntity>) {
        try {
            if (list.isEmpty()) {
                Log.d(TAG, "checkIsDownload: FIle not Download")
                pdfDownloadMain()
            } else {
                loadPdfByPath(list[0].file_path)
                Log.d(TAG, "checkIsDownload: FIle Downloaded")
            }
        } catch (e: Exception) {

        }


    }

    private fun loadPdfByPath(path: String) {
        binding.rvPdfDownloading.visibility = View.GONE
        binding.pdfView.visibility = View.VISIBLE
        binding.pdfView.fromFile(path).show()
    }

    private fun pdfDownloadMain() {
        pdfDownloadAdapter = PdfDownloadAdapter(
            binding.root.context,
            list,
            object : PdfDownloadAdapter.OnMyPdfDownloadClickListener {
                override fun onPdfDownloadClick(
                    pdfDownload: PdfDownload,
                    position: Int,
                    downloadView: View,
                    loadingView: View,
                    downloadingCardView: View,
                    pdfView: PDFView,
                    cardMain: View
                ) {
                    downloadView.visibility = View.GONE
                    loadingView.visibility = View.VISIBLE
                    downloadingCardView.visibility = View.VISIBLE

                    downloadPdf(
                        pdfDownload,
                        position,
                        downloadView,
                        loadingView,
                        downloadingCardView,
                        pdfView,
                        cardMain
                    )

                }

                private fun downloadPdf(
                    pdfDownload: PdfDownload,
                    position: Int,
                    downloadView: View,
                    loadingView: View,
                    downloadingCardView: View,
                    pdfView: PDFView,
                    cardMain: View
                ) {

                    val service = ApiClientPdf().service
                    list[position].pdfUrl?.let {
                        service.getPdf(it).enqueue(object : Callback<ResponseBody> {

                            val context = binding.root.context
                            val path =
                                "${context.filesDir.absolutePath}/${list[position].pdfName}.pdf"

                            override fun onResponse(
                                call: Call<ResponseBody>?,
                                response: Response<ResponseBody>?
                            ) {
                                Log.d(TAG, "onResponse: ")
                                if (response!!.isSuccessful) {
                                    Log.d(TAG, "onResponse: Success qaytdi")
                                    writeResponseBodyToDisk(response.body(), path, pdfView)
                                    Log.d(TAG, "onResponse: after write $path")
                                    val fileEntity = FileEntity()
                                    fileEntity.file_name = list[position].pdfName.toString()
                                    fileEntity.file_path = path
                                    fileDatabase.fileDao().addFilePathAndName(fileEntity)
                                    //                                cardMain.visibility = View.GONE
                                    //                                downloadingCardView.visibility = View.GONE
                                    //                                pdfView.visibility = View.VISIBLE
                                    //                                pdfView.fromFile(path).show()
                                    openPdfWithAdapter(path)

                                }

                            }

                            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                                Log.d(TAG, "onFailure: ${t!!.message}")
                                Toast.makeText(
                                    binding.root.context,
                                    "Check your internet",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                    }
                }


                fun writeResponseBodyToDisk(
                    body: ResponseBody,
                    filePath: String,
                    pdfView: PDFView
                ): Boolean {
                    try {
                        var mediaFile = File(filePath)
                        var inputStream: InputStream? = null
                        var outputStream: OutputStream? = null
                        try {
                            val fileReader = ByteArray(4096)
                            val fileSize = body.contentLength()
                            var fileSizeDownloaded: Long = 0
                            inputStream = body.byteStream()
                            outputStream = FileOutputStream(mediaFile)
                            while (true) {
                                val read = inputStream.read(fileReader)
                                if (read == -1) {
                                    break
                                }
                                outputStream.write(fileReader, 0, read)
                                fileSizeDownloaded += read.toLong()
                                Log.d(TAG, "file download: $fileSizeDownloaded of $fileSize")


                            }
                            outputStream.flush()
                            return true
                        } catch (e: IOException) {
                            return false
                        } finally {
                            inputStream?.close()
                            outputStream?.close()
                        }
                    } catch (e: IOException) {
                        return false
                    }
                }


            })

        binding.rvPdfDownloading.adapter = pdfDownloadAdapter
    }

    private fun openPdfWithAdapter(path: String) {
        binding.rvPdfDownloading.visibility = View.GONE
        binding.pdfView.fromFile(path).show()
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

    private fun loadUzData() {
        downloadText = "Yuklanmoqda..."
        listVideo = ArrayList()
        listVideo.clear()
        listVideo.add(Video("AAAAAAAAAAAAAAAAAAAAAAAA", "Jq608TcU_g8", ""))
        listVideo.add(Video("AAAAAAAAAAAAAAAAAAAAAAAA", "Jq608TcU_g8", ""))
        list = ArrayList()
        list.clear()
        list.add(
            PdfDownload(
                "u/0/uc?id=1SL8SnCxTvLbGCaVEKwHrSfUKOlPru_BK&export=download",
                "Qo'zg'aluvchanlik korreksiyasi"
            )
        )

    }

    private fun loadKrillData() {
        downloadText = "Юкланмоқда..."
        listVideo = ArrayList()
        listVideo.clear()
        listVideo.add(Video("AAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAA", ""))
        listVideo.add(Video("AAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAA", ""))
        list = ArrayList()
        list.clear()
        list.add(
            PdfDownload(
                "u/0/uc?id=1rY7hNCncsqDw1T-5ctPYGMeTjPrLXGBM&export=download",
                "Қўзғалувчанлик коррекцияси"
            )
        )

    }

    private fun loadRuData() {
        downloadText = "Загрузка..."
        listVideo = ArrayList()
        listVideo.clear()
        listVideo.add(Video("AAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAA", ""))
        listVideo.add(Video("AAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAA", ""))
        listVideo.add(Video("AAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAA", ""))
        list = ArrayList()
        list.clear()
        list.add(
            PdfDownload(
                "u/0/uc?id=18lqgJv6AXJboOgsn0h4qPp7k536Q4qle&export=download",
                "Коррекция возбудимости"
            )
        )

    }

}