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
import uz.hamroev.bardambolnew.databinding.FragmentContent1Binding
import uz.hamroev.bardambolnew.model.Content


class Content1Fragment : Fragment() {


    lateinit var binding: FragmentContent1Binding

    lateinit var list: ArrayList<Content>
    lateinit var contentAdapter: ContentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContent1Binding.inflate(layoutInflater, container, false)

        checkLanguage()
        contentAdapter = ContentAdapter(
            binding.root.context,
            list,
            object : ContentAdapter.OnMyContentClickListener {
                override fun onContentClick(content: Content, position: Int) {
                    when (position) {

                        0 -> {
                            findNavController().navigate(R.id.content1_1Fragment)
                        }
                        1 -> {
                            findNavController().navigate(R.id.content1_2Fragment)
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
                "Ekstremal vaziyatlarda o'zini tutish qoidalari",
                R.drawable.ic_medicine,
                R.drawable.back_image_content1
            )
        )
        list.add(
            Content(
                "Shoshilinch psixologik yordam",
                R.drawable.ic_medicine,
                R.drawable.back_image_content1
            )
        )
    }

    private fun loadKrillData() {
        list = ArrayList()
        list.clear()
        list.add(
            Content(
                "Экстремал вазиятларда ўзини тутиш қоидалари",
                R.drawable.ic_medicine,
                R.drawable.back_image_content1
            )
        )
        list.add(
            Content(
                "Шошилинч психологик ёрдам",
                R.drawable.ic_medicine,
                R.drawable.back_image_content1
            )
        )
    }

    private fun loadRuData() {
        list = ArrayList()
        list.clear()
        list.add(
            Content(
                "Правила поведения в экстренных ситуациях",
                R.drawable.ic_medicine,
                R.drawable.back_image_content1
            )
        )
        list.add(
            Content(
                "Экстренная психологическая помощь",
                R.drawable.ic_medicine,
                R.drawable.back_image_content1
            )
        )
    }


}