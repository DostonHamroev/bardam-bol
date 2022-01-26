package uz.hamroev.bardambolnew.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.hamroev.bardambolnew.databinding.FragmentContent3Binding

class Content3Fragment : Fragment() {


    lateinit var binding: FragmentContent3Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContent3Binding.inflate(layoutInflater, container, false)


        return binding.root

    }


}