package uz.hamroev.bardambolnew.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.hamroev.bardambolnew.databinding.FragmentContent2Binding


class Content2Fragment : Fragment() {


    lateinit var binding: FragmentContent2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContent2Binding.inflate(layoutInflater, container, false)


        return binding.root
    }


}