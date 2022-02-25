package uz.hamroev.bardambolnew.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.hamroev.bardambolnew.R
import uz.hamroev.bardambolnew.adapter.ContentAdapter
import uz.hamroev.bardambolnew.cache.Cache
import uz.hamroev.bardambolnew.databinding.FragmentContent2Binding
import uz.hamroev.bardambolnew.model.Content


class Content2Fragment : Fragment() {


    lateinit var binding: FragmentContent2Binding

    lateinit var list: ArrayList<Content>
    lateinit var contentAdapter: ContentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContent2Binding.inflate(layoutInflater, container, false)

        checkLanguage()
        contentAdapter = ContentAdapter(
            binding.root.context,
            list,
            object : ContentAdapter.OnMyContentClickListener {
                override fun onContentClick(content: Content, position: Int) {

                    when (position) {
                        0 -> {
                            findNavController().navigate(R.id.birinchiTibbiyYordamFragment)
                        }
                        1 -> {
                            findNavController().navigate(R.id.content2Into2Fragment)
                        }
                        2 -> {
                            findNavController().navigate(R.id.bolagaBirinchiYordamVideoFragment)
                        }
                        3 -> {
                            findNavController().navigate(R.id.kattalargaBirinchiYordamVideoFragment)
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
        list.add(
            Content(
                "Birinchi tibbiy yordam",
                R.drawable.ic_birinchi_tibbiy_yordam,
                R.drawable.back_image_content2
            )
        )
        list.add(
            Content(
                "Sog'lom turmush tarzi",
                R.drawable.ic_soglom_turmush_tarzi,
                R.drawable.back_image_content2
            )
        )
        list.add(
            Content(
                "Bolalarga birinchi tibbiy Yordam (Video)",
                R.drawable.ic_bolaga_tibbiy_yordam,
                R.drawable.back_image_content2
            )
        )
        list.add(
            Content(
                "Kattalarga birinchi tibbiy yordam (Video)",
                R.drawable.ic_kattalarga_birinchi_yordam,
                R.drawable.back_image_content2
            )
        )
    }

    private fun loadKrillData() {
        list = ArrayList()
        list.clear()
        list.add(
            Content(
                "Биринчи тиббий ёрдам",
                R.drawable.ic_birinchi_tibbiy_yordam,
                R.drawable.back_image_content2
            )
        )
        list.add(
            Content(
                "Соғлом турмуш тарзини",
                R.drawable.ic_soglom_turmush_tarzi,
                R.drawable.back_image_content2
            )
        )
        list.add(
            Content(
                "Болаларга биринчи тиббий ёрдам (Видео)",
                R.drawable.ic_bolaga_tibbiy_yordam,
                R.drawable.back_image_content2
            )
        )
        list.add(
            Content(
                "Катталарга биринчи тиббий ёрдам (Видео)",
                R.drawable.ic_kattalarga_birinchi_yordam,
                R.drawable.back_image_content2
            )
        )
    }

    private fun loadRuData() {
        list = ArrayList()
        list.clear()
        list.add(
            Content(
                "Первая медицинская помощь",
                R.drawable.ic_birinchi_tibbiy_yordam,
                R.drawable.back_image_content2
            )
        )
        list.add(
            Content(
                "Здоровый образ жизни",
                R.drawable.ic_soglom_turmush_tarzi,
                R.drawable.back_image_content2
            )
        )
        list.add(
            Content(
                "Первая  доврачебная помощь детям (видео)",
                R.drawable.ic_bolaga_tibbiy_yordam,
                R.drawable.back_image_content2
            )
        )
        list.add(
            Content(
                "Первая доврачебная помощь взрослым (видео)",
                R.drawable.ic_kattalarga_birinchi_yordam,
                R.drawable.back_image_content2
            )
        )
    }

}