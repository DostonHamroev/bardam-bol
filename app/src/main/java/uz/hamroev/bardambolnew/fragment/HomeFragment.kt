package uz.hamroev.bardambolnew.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.hamroev.bardambolnew.R
import uz.hamroev.bardambolnew.adapter.MenuMainAdapter
import uz.hamroev.bardambolnew.cache.Cache
import uz.hamroev.bardambolnew.databinding.FragmentHomeBinding
import uz.hamroev.bardambolnew.model.MainMenu


class HomeFragment : Fragment() {


    lateinit var binding: FragmentHomeBinding
    lateinit var list: ArrayList<MainMenu>
    lateinit var menuMainAdapter: MenuMainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        Cache.init(binding.root.context)
        checkLanguage()



        menuMainAdapter = MenuMainAdapter(
            binding.root.context,
            list,
            object : MenuMainAdapter.OnMyMainMenuClickLIstener {
                override fun onClickMenu(mainMenu: MainMenu, position: Int) {
                    when (position) {
                        0 -> {
                            findNavController().navigate(R.id.content1Fragment)
                        }
                        1 -> {
                            findNavController().navigate(R.id.content2Fragment)
                        }
                        2 -> {
                            findNavController().navigate(R.id.content3Fragment)
                        }
                        3 -> {
                            findNavController().navigate(R.id.certificatFragment)
                        }
                        4 -> {
                            findNavController().navigate(R.id.authorFragment)
                        }

                    }
                }
            })
        binding.rvMain.adapter = menuMainAdapter



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
        binding.typeWriter.animateText("Ekstremal vaziyatlarda o'zini boshqarish!")
        binding.typeWriter.setCharacterDeley(50)
        list = ArrayList()
        list.clear()
        list.add(MainMenu("Ekstremal vaziyatlarni boshqarish", R.drawable.ic_doctor2))
        list.add(MainMenu("O'ziga-o'zi birinchi yordam ko'rsatish", R.drawable.ic_doctor1))
        list.add(MainMenu("Bolaga g'amxo'rlik. Bolaga yordam ko'rsatish", R.drawable.ic_children_father))
        list.add(MainMenu("Guvohnoma", R.drawable.ic_certificat_girl))
        list.add(MainMenu("Ilova haqida", R.drawable.ic_app_about))
    }

    private fun loadKrillData() {
        binding.typeWriter.animateText("Экстремал вазиятларда ўзини бошкариш!")
        binding.typeWriter.setCharacterDeley(50)
        list = ArrayList()
        list.clear()
        list.add(MainMenu("Экстремал вазиятларни бошқариш", R.drawable.ic_doctor2))
        list.add(MainMenu("Ўзига-ўзи биринчи ёрдам кўрсатиш", R.drawable.ic_doctor1))
        list.add(MainMenu("Болага ғамхўрлик. Болага ёрдам кўрсатиш", R.drawable.ic_children_father))
        list.add(MainMenu("Гувоҳнома", R.drawable.ic_certificat_girl))
        list.add(MainMenu("Илова ҳақида", R.drawable.ic_app_about))
    }

    private fun loadRuData() {
        binding.typeWriter.animateText("Что делать во время экстремальных ситуаций!")
        binding.typeWriter.setCharacterDeley(50)
        list = ArrayList()
        list.clear()
        list.add(MainMenu("Управление экстремальными ситуациями", R.drawable.ic_doctor2))
        list.add(MainMenu("Оказание первой доврачебной", R.drawable.ic_doctor1))
        list.add(MainMenu("Забота о ребенке. Оказание помощи ребенку", R.drawable.ic_children_father))
        list.add(MainMenu("Сертификат", R.drawable.ic_certificat_girl))
        list.add(MainMenu("О приложении", R.drawable.ic_app_about))

    }


}