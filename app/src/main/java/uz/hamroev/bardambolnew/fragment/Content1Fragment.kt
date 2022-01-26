package uz.hamroev.bardambolnew.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.hamroev.bardambolnew.databinding.FragmentContent1Binding


class Content1Fragment : Fragment() {


    lateinit var binding: FragmentContent1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContent1Binding.inflate(layoutInflater, container, false)


        return binding.root
    }


}