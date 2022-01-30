package uz.hamroev.bardambolnew.fragment.content1.content1_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.hamroev.bardambolnew.adapter.VideoAdapter
import uz.hamroev.bardambolnew.cache.Cache
import uz.hamroev.bardambolnew.databinding.FragmentSuvToshqinBinding
import uz.hamroev.bardambolnew.model.Video

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SuvToshqinFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SuvToshqinFragment : Fragment() {
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

    lateinit var binding: FragmentSuvToshqinBinding
    lateinit var list: ArrayList<Video>
    lateinit var videoAdapter: VideoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSuvToshqinBinding.inflate(layoutInflater, container, false)

        checkLanguage()
        videoAdapter =
            VideoAdapter(binding.root.context, list, object : VideoAdapter.OnMyVideoClickListener {
                override fun onDownload(video: Video, position: Int, view: View) {
                    when (position) {
                        0 -> {
                            view.visibility = View.GONE
                        }
                        0 -> {
                        }
                        0 -> {
                        }
                    }
                }
            })
        binding.rvVideo.adapter = videoAdapter


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

    private fun loadUzData() {
        list = ArrayList()
        list.clear()
        list.add(Video("Zilzila Lite", "k3rDBN2it-c", "Yuklash"))
        list.add(Video("Zilzila Lite", "k3rDBN2it-c", "Yuklash"))
        list.add(Video("Zilzila Lite", "k3rDBN2it-c", "Yuklash"))

    }

    private fun loadKrillData() {
        list = ArrayList()
        list.clear()
        list.add(Video("", "fqfN6U23Sfk", "Юклаш"))
        list.add(Video("", "fqfN6U23Sfk", "Юклаш"))
        list.add(Video("", "fqfN6U23Sfk", "Юклаш"))

    }

    private fun loadRuData() {
        list = ArrayList()
        list.clear()
        list.add(Video("действия при землятресении", "fqfN6U23Sfk", "Скачать"))
        list.add(Video("действия при землятресении", "fqfN6U23Sfk", "Скачать"))
        list.add(Video("действия при землятресении", "fqfN6U23Sfk", "Скачать"))

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SuvToshqinFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SuvToshqinFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}