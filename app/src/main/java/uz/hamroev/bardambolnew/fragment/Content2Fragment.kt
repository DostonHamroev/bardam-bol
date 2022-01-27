package uz.hamroev.bardambolnew.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        list.add(Content("Birinhi tibbiy yordam", R.drawable.ic_medicine, R.drawable.back_image_content2))
        list.add(Content("Sog'lom Turmush Tarzi", R.drawable.ic_medicine, R.drawable.back_image_content2))
        list.add(Content("Bolalarga Birinchi Tibbiy Yordam (Video)", R.drawable.ic_medicine, R.drawable.back_image_content2))
        list.add(Content("Kattalarga Birinchi Tibbiy Yordam (Video)", R.drawable.ic_medicine, R.drawable.back_image_content2))
    }

    private fun loadKrillData() {
        list = ArrayList()
        list.clear()
        list.add(Content("Биринчи тиббий ёрдам", R.drawable.ic_medicine, R.drawable.back_image_content2))
        list.add(Content("Соглом турмуш тарзини", R.drawable.ic_medicine, R.drawable.back_image_content2))
        list.add(Content("Болаларга Биринчи Тиббий Ёрдам (Видео)", R.drawable.ic_medicine, R.drawable.back_image_content2))
        list.add(Content("Катталарга Биринчи Тиббий Ёрдам (Видео)", R.drawable.ic_medicine, R.drawable.back_image_content2))
    }

    private fun loadRuData() {
        list = ArrayList()
        list.clear()
        list.add(Content("первая медицинская помощь", R.drawable.ic_medicine, R.drawable.back_image_content2))
        list.add(Content("Здоровый образ жизни", R.drawable.ic_medicine, R.drawable.back_image_content2))
        list.add(Content("Первая помощь детям (видео)", R.drawable.ic_medicine, R.drawable.back_image_content2))
        list.add(Content("Первая помощь взрослым (видео)", R.drawable.ic_medicine, R.drawable.back_image_content2))
    }

}