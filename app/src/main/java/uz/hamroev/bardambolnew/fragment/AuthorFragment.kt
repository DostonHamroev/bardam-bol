package uz.hamroev.bardambolnew.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.hamroev.bardambolnew.R
import uz.hamroev.bardambolnew.adapter.UserAdapter
import uz.hamroev.bardambolnew.cache.Cache
import uz.hamroev.bardambolnew.databinding.FragmentAuthorBinding
import uz.hamroev.bardambolnew.model.User

class AuthorFragment : Fragment() {


    lateinit var binding: FragmentAuthorBinding
    lateinit var list: ArrayList<User>
    lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorBinding.inflate(layoutInflater, container, false)

        checkLanguage()
        userAdapter = UserAdapter(binding.root.context, list)
        binding.rvUser.adapter = userAdapter


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

    private fun loadRuData() {
        list = ArrayList()
        list.clear()
        list.add(User("Камилова Нодира \nҒайратовна", "Профессор"+"\nд.п.н."+"\nПрофессор психологии ТГПУ имени Низами", R.drawable.ic_nodira))
        list.add(User("Бекназарова Саида \nСафибуллаевна", "Профессор"+"\nд.т.н."+"\nПрофессор кафедры аудиовизуальные технологии Ташкентского университета информационных технологий имени Мухаммада ал-Хорезми", R.drawable.ic_saida))
        list.add(User("Шарипова Диляра \nДжуманиязовна","Доктор педагогических наук, профессор кафедры \"Зоологии и анатомии\" Ташкентского педагогического университета им.Низами.", R.drawable.ic_dilyara))
        list.add(User("Турдиева Кавсар \nШералиевна","Ташкентский медицинский институт Педиатрии кафедра  «Узбекского языка и литературы»\n" + "Заведующий кафедрой", R.drawable.ic_kavsar))
        list.add(User("Омонова Умида \nТулкиновна","Доктор медицинских наук"+"\nдоцент"+"\nТашкентский медицинский институт педиатрии, кафедра болезней нервной системы, болезней детской нервной системы и медицинской генетики.", R.drawable.ic_umida))
        list.add(User("Мардонов Алишер \nАбдуназарович","С января 2021 года - заместитель начальника медицинского управления МЧС Республики Узбекистан.", R.drawable.ic_alisher))
        list.add(User("Мухамедова Мадинахон \nБаходировна","Дошкольное образовательное организация № 4 «Камалак» МВД РУз\nпсихолог", R.drawable.ic_madina))
        list.add(User("Йигиталиев Бобурмирзо \nБахтиёр угли","Центр изучения региональных угроз, медиа-эксперт", R.drawable.ic_bobur))
        list.add(User("Достон Ҳамроев \nДавронович", "Андроид разработчик"+"\nмобильный дизайнер"+"\nСтудент Ташкентского университета информационных технологий имени Мухаммада ал-Хорезми", R.drawable.ic_doston))

    }

    private fun loadKrillData() {
        list = ArrayList()
        list.clear()
        list.add(User("Камилова Нодира \nҒайратовна", "Профессор"+"\nп.ф.д."+ "\nНизомий номидаги ТДПУ  Психология кафедрасининг профессори", R.drawable.ic_nodira))
        list.add(User("Бекназарова Саида \nСафибуллаевна", "Профессор"+ "\nт.ф.д."+"\nМухаммад ал-Хоразми номидаги Тошкент Ахборот технологиялари университети   Аудиовизуал технологиялар» кафедраси профессори", R.drawable.ic_saida))
        list.add(User("Шарипова Дилара \nДжуманиязовна","\"Низомий номидаги ТДПУ Зоология ва анатомия\" кафедраси профессори, педагогика фанларни доктори, профессор", R.drawable.ic_dilyara))
        list.add(User("Турдиева Кавсар \nШералиевна","Тошкент Педиатрия тиббиети институти  «Узбек тили ва адабиёти»\n" + "кафедраси мудири", R.drawable.ic_kavsar))
        list.add(User("Омонова Умида \nТулкиновна","Тиббиет фанлари доктори"+"\nдоцент"+"\nТошкент Педиатрия тиббиёт институти, Асаб касалликлари, болалар асаб касалликлари ва тиббий генетика кафедраси.", R.drawable.ic_umida))
        list.add(User("Мардонов Алишер \nАбдуназарович","2021 йил январь ойидан Ўзбекистон Республикаси Фавқулодда вазиятлар вазирлиги Тиббиёт бошқармаси бошлиғининг ўринбосари", R.drawable.ic_alisher))
        list.add(User("Мухамедова Мадинахон \nБаходировна.","Ўзбекистон Республикаси Ички Ишлар Вазирлиги 4- сонли “Камалак” мактабгача таълим ташкилоти", R.drawable.ic_madina))
        list.add(User("Йигиталиев Бобурмирзо \nБахтиёр угли","Минтақавий таҳдидларни ўрганиш бўйича марказ, медиа-эксперти", R.drawable.ic_bobur))
        list.add(User("Достон Ҳамроев \nДавронович","Андроид дастурчи"+ "\nUI дизайнер"+"\nМуҳаммад ал-Хоразмий номидаги Тошкент Ахборот технологиялари университети талабаси", R.drawable.ic_doston))

    }

    private fun loadUzData() {
        list = ArrayList()
        list.clear()
        list.add(User("Kamilova Nodira \nG'ayratovna","Professor"+ "\np.f.d."+ "\nNizomiy nomidagi TDPU Psixologiya kafedrasining professori", R.drawable.ic_nodira))
        list.add(User("Beknazarova Saida \nSafibullayevna", "Professor"+ "\nt.f.d."+ "\nMuhammad al-Xorazmiy nomidagi Toshkent Аxborot texnologiyalari universiteti Аudiovizual texnologiyalar kafedrasi professori", R.drawable.ic_saida))
        list.add(User("Sharipova Dilara \nJumaniyazovna","\"Nizomiy nomidagi TDPU  Zoologiya va anatomiya\" kafedrasi professori, pedagogika fanlarni doktori, professor", R.drawable.ic_dilyara))
        list.add(User("Turdieva Kavsar \nSheralievna","Toshkent Pediatriya tibbieti instituti «Uzbek tili va adabiyoti»\n" + "kafedrasi mudiri", R.drawable.ic_kavsar))
        list.add(User("Omonova Umida \nTulkinovna","Tibbiyot fanlari doktori"+"\nDotsent"+"\nToshkent Pediatriya tibbiyot instituti, Аsab kasalliklari, bolalar asab kasalliklari va tibbiy genetika kafedrasi", R.drawable.ic_umida))
        list.add(User("Mardonov Аlisher \nАbdunazarovich","2021 yil yanvar' oyidan O‘zbekiston Respublikasi Favqulodda vaziyatlar vazirligi Tibbiyot boshqarmasi boshlig‘ining o‘rinbosari", R.drawable.ic_alisher))
        list.add(User("Muxamedova Madinaxon \nBaxodirovna","O’zbekiston Respublikasi Ichki Ishlar Vazirligi 4- sonli “Kamalak” maktabgacha ta’lim tashkiloti", R.drawable.ic_madina))
        list.add(User("Yigitaliev Boburmirzo \nBaxtiyor o'g'li","Mintaqaviy tahdidlarni o‘rganish bo‘yicha markaz, media-eksperti", R.drawable.ic_bobur))
        list.add(User("Doston Hamroyev \nDavron o'g'li","Android Dasturchi"+ "\nMobile Designer"+"\nMuhammad al-Xorazmiy nomidagi Toshkent Аxborot texnologiyalari universiteti talabasi", R.drawable.ic_doston))


    }

}