package uz.hamroev.bardambolnew.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.hamroev.bardambolnew.R
import uz.hamroev.bardambolnew.adapter.ContentAdapter
import uz.hamroev.bardambolnew.cache.Cache
import uz.hamroev.bardambolnew.databinding.FragmentContent12Binding
import uz.hamroev.bardambolnew.model.Content

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Content1_2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Content1_2Fragment : Fragment() {
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

    lateinit var binding: FragmentContent12Binding
    lateinit var list: ArrayList<Content>
    lateinit var contentAdapter: ContentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContent12Binding.inflate(layoutInflater, container, false)

        checkLanguage()

        contentAdapter = ContentAdapter(
            binding.root.context,
            list,
            object : ContentAdapter.OnMyContentClickListener {
                override fun onContentClick(content: Content, position: Int) {

                }
            })
        binding.rvContent.adapter = contentAdapter

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
        list.add(Content("Tajovuz", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Befarqlik", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Dam olish uchun audio", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Aldanishlar va gallyutsinatsiyalar", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Favqulodda vaziyatlarda bolalar bilan o'zaro munosabat", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Vosita qo'zg'alishi", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Falokatda birinchi yordam", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Isterika", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Asabiy titroq", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Yig'lamoq", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Bolalarni parvarish qilish qoidalari", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Asabiy taranglikni kamaytirish", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Qo'rquv", R.drawable.ic_2_1, R.drawable.back_image_content1))

    }

    private fun loadKrillData() {
        list = ArrayList()
        list.clear()
        list.add(Content("Тажовуз", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Бефарқлик", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Дам олиш учун аудио", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Aлданишлар ва галлюцинациялар", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Фавқулодда вазиятларда болалар билан ўзаро муносабат", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Восита қўзғалиши", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Фалокатда биринчи ёрдам", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Истерика", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Aсабий титроқ", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Йиғламоқ", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Болаларни парвариш қилиш қоидалари", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Aсабий тарангликни камайтириш", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("Қўрқув", R.drawable.ic_2_1, R.drawable.back_image_content1))

    }

    private fun loadRuData() {
        list = ArrayList()
        list.clear()
        list.add(Content("агрессия", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("апатия", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("аудио для релаксации", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("бред и галлюцинации", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("взаимодействие с детьми при  ЧС", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("двигательное возбуждение", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("первая помощь в случае бедствия", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("истерика", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("нервная дрожь", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("плач", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("правила помощи детям", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("снижение нервно псих напряжения", R.drawable.ic_2_1, R.drawable.back_image_content1))
        list.add(Content("страх", R.drawable.ic_2_1, R.drawable.back_image_content1))

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Content1_2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Content1_2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}