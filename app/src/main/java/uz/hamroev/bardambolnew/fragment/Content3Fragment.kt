package uz.hamroev.bardambolnew.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.hamroev.bardambolnew.R
import uz.hamroev.bardambolnew.adapter.ContentAdapter
import uz.hamroev.bardambolnew.cache.Cache
import uz.hamroev.bardambolnew.databinding.FragmentContent3Binding
import uz.hamroev.bardambolnew.model.Content

class Content3Fragment : Fragment() {


    lateinit var binding: FragmentContent3Binding

    lateinit var list: ArrayList<Content>
    lateinit var contentAdapter: ContentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContent3Binding.inflate(layoutInflater, container, false)

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
        list.add(Content("", R.drawable.ic_medicine, R.drawable.ic_launcher_background))
        list.add(Content("", R.drawable.ic_medicine, R.drawable.ic_launcher_background))
        list.add(Content("", R.drawable.ic_medicine, R.drawable.ic_launcher_background))
    }

    private fun loadKrillData() {
        list = ArrayList()
        list.clear()
        list.add(Content("", R.drawable.ic_medicine, R.drawable.ic_launcher_background))
        list.add(Content("", R.drawable.ic_medicine, R.drawable.ic_launcher_background))
        list.add(Content("", R.drawable.ic_medicine, R.drawable.ic_launcher_background))
    }

    private fun loadRuData() {
        list = ArrayList()
        list.clear()
        list.add(Content("", R.drawable.ic_medicine, R.drawable.ic_launcher_background))
        list.add(Content("", R.drawable.ic_medicine, R.drawable.ic_launcher_background))
        list.add(Content("", R.drawable.ic_medicine, R.drawable.ic_launcher_background))
    }

}