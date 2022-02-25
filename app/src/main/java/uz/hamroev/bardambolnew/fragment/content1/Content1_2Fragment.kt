package uz.hamroev.bardambolnew.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
                    when (position) {
                        0 -> {
                            findNavController().navigate(R.id.tajovuzFragment)
                        }
                        1 -> {
                            findNavController().navigate(R.id.befarqlikFragment)
                        }
                        2 -> {
                            findNavController().navigate(R.id.damOlishFragment)
                        }
                        3 -> {
                            findNavController().navigate(R.id.aldanishlarFragment)
                        }
                        4 -> {
                            findNavController().navigate(R.id.favqulotdaVaziyatlarFragment)
                        }
                        5 -> {
                            findNavController().navigate(R.id.vositaQozgalishiFragment)
                        }
                        6 -> {
                            findNavController().navigate(R.id.falokatdaBirinchiYordamFragment)
                        }
                        7 -> {
                            findNavController().navigate(R.id.isterikaFragment)
                        }
                        8 -> {
                            findNavController().navigate(R.id.asabiyTitroqFragment)
                        }
                        9 -> {
                            findNavController().navigate(R.id.yiglamoqFragment)
                        }
                        10 -> {
                            findNavController().navigate(R.id.bolaniParvarishlashFragment)
                        }
                        11 -> {
                            findNavController().navigate(R.id.asabiyTaranglikFragment)
                        }
                        12 -> {
                            findNavController().navigate(R.id.qorquvFragment)
                        }
                    }
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
        list.add(Content("Tajovuz holatida birinchi yordam", R.drawable.ic_tajovuz, R.drawable.back_image_content1))
        list.add(Content("Loqaydlik holatida birinchi yordam", R.drawable.ic_bafarqlik, R.drawable.back_image_content1))
        list.add(
            Content(
                "Relaksatsiya mashqi",
                R.drawable.ic_audio_dam_olish,
                R.drawable.back_image_content1
            )
        )
        list.add(
            Content(
                "Alahlash va gallyutsinatsiyalar holatida birinchi yordam",
                R.drawable.ic_aldanishlar,
                R.drawable.back_image_content1
            )
        )
        list.add(
            Content(
                "Bolalarga birinchi yordam usullari",
                R.drawable.ic_favquloda_bolalar_bilan_munosabat,
                R.drawable.back_image_content1
            )
        )
        list.add(
            Content(
                "Harakatli qo'zg'alish holatida birinchi yordam",
                R.drawable.ic_vosita_qozgalishi,
                R.drawable.back_image_content1
            )
        )
        list.add(
            Content(
                "Distress holatida birinchi yordam",
                R.drawable.ic_favqulotda_birinchi_yordam,
                R.drawable.back_image_content1
            )
        )
        list.add(Content("Isterika holatida birinchi yordam", R.drawable.ic_isterika, R.drawable.back_image_content1))
        list.add(
            Content(
                "Asabiy titroq holatida birinchi yordam",
                R.drawable.ic_asabiy_titroq,
                R.drawable.back_image_content1
            )
        )
        list.add(Content("Yig'ida birinchi yordam", R.drawable.ic_yiglamoq, R.drawable.back_image_content1))
        // list.add(Content("Bolalarni parvarish qilish qoidalari", R.drawable.ic_bola_parvarish_qilish, R.drawable.back_image_content1))
        list.add(
            Content(
                "Asabiy zo'riqishni pasaytirish",
                R.drawable.ic_asabiy_taranglikni_kamaytirish,
                R.drawable.back_image_content1
            )
        )
        list.add(Content("Qo'rquv holatida birinchi yordam", R.drawable.ic_qorquv, R.drawable.back_image_content1))

    }

    private fun loadKrillData() {
        list = ArrayList()
        list.clear()
        list.add(Content("Тажовуз ҳолатида биринчи ёрдам", R.drawable.ic_tajovuz, R.drawable.back_image_content1))
        list.add(Content("Лоқайдлик ҳолатида биринчи ёрдам", R.drawable.ic_bafarqlik, R.drawable.back_image_content1))
        list.add(
            Content(
                "Релаксация машқи",
                R.drawable.ic_audio_dam_olish,
                R.drawable.back_image_content1
            )
        )
        list.add(
            Content(
                "Алаҳлаш ва галлютсинатсиялар ҳолатида биринчи ёрдам",
                R.drawable.ic_aldanishlar,
                R.drawable.back_image_content1
            )
        )
        list.add(
            Content(
                "Болаларга биринчи ёрдам усуллари",
                R.drawable.ic_favquloda_bolalar_bilan_munosabat,
                R.drawable.back_image_content1
            )
        )
        list.add(
            Content(
                "Ҳаракатли қўзғалишда биринчи ёрдам",
                R.drawable.ic_vosita_qozgalishi,
                R.drawable.back_image_content1
            )
        )
        list.add(
            Content(
                "Дистресс холатида биринчи ёрдам",
                R.drawable.ic_favqulotda_birinchi_yordam,
                R.drawable.back_image_content1
            )
        )
        list.add(Content("Истерика ҳолатида биринчи ёрдам", R.drawable.ic_isterika, R.drawable.back_image_content1))
        list.add(
            Content(
                "Aсабий титроқ ҳолатида биринчи ёрдам",
                R.drawable.ic_asabiy_titroq,
                R.drawable.back_image_content1
            )
        )
        list.add(Content("Йиғида биринчи ёрдам", R.drawable.ic_yiglamoq, R.drawable.back_image_content1))
         // list.add(Content("Болаларни парвариш қилиш қоидалари", R.drawable.ic_bola_parvarish_qilish, R.drawable.back_image_content1))
        list.add(
            Content(
                "Aсабий зўриқишни пасайтириш",
                R.drawable.ic_asabiy_taranglikni_kamaytirish,
                R.drawable.back_image_content1
            )
        )
        list.add(Content("Қўрқув ҳолатида биринчи ёрдам", R.drawable.ic_qorquv, R.drawable.back_image_content1))

    }

    private fun loadRuData() {
        list = ArrayList()
        list.clear()
        list.add(Content("Первая помощь в состоянии агрессии", R.drawable.ic_tajovuz, R.drawable.back_image_content1))
        list.add(Content("Первая помощь при апатии", R.drawable.ic_bafarqlik, R.drawable.back_image_content1))
        list.add(
            Content(
                "Аудио для релаксации",
                R.drawable.ic_audio_dam_olish,
                R.drawable.back_image_content1
            )
        )
        list.add(
            Content(
                "Первая помощь при бреде и галлюцинациях",
                R.drawable.ic_aldanishlar,
                R.drawable.back_image_content1
            )
        )
        list.add(Content("Правила первой психологической помощи детям", R.drawable.ic_favquloda_bolalar_bilan_munosabat, R.drawable.back_image_content1))
        list.add(
            Content(
                "Первая помощь при двигательном возбуждении",
                R.drawable.ic_vosita_qozgalishi,
                R.drawable.back_image_content1
            )
        )
        list.add(
            Content(
                "Первая помощь при дистрессе",
                R.drawable.ic_favqulotda_birinchi_yordam,
                R.drawable.back_image_content1
            )
        )
        list.add(Content("Первая помощь в состоянии истерики", R.drawable.ic_isterika, R.drawable.back_image_content1))
        list.add(
            Content(
                "Первая помощь при нервной дрожи",
                R.drawable.ic_asabiy_titroq,
                R.drawable.back_image_content1
            )
        )
        list.add(Content("Первая помощь в состоянии плача", R.drawable.ic_yiglamoq, R.drawable.back_image_content1))
        // list.add(Content("Правила помощи детям", R.drawable.ic_bola_parvarish_qilish, R.drawable.back_image_content1))
        list.add(
            Content(
                "Снижение нервно-психологического напряжения",
                R.drawable.ic_asabiy_taranglikni_kamaytirish,
                R.drawable.back_image_content1
            )
        )
        list.add(Content("Первая помощь в состоянии страха", R.drawable.ic_qorquv, R.drawable.back_image_content1))

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