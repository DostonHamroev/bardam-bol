package uz.hamroev.bardambolnew.activity

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import com.permissionx.guolindev.PermissionX
import uz.hamroev.bardambolnew.R
import uz.hamroev.bardambolnew.cache.Cache
import uz.hamroev.bardambolnew.databinding.ActivityHomeBinding
import uz.hamroev.bardambolnew.databinding.DialogLanguageBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
//
//    lateinit var dataPasser: uz.hamroev.bardambolnew.passData.OnDataPass

    var til = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Cache.init(this)
        checkLanguage()
//
//        dataPasser = applicationContext as OnDataPass


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        supportActionBar?.hide()

        PermissionX.init(this)
            .permissions(
                android.Manifest.permission.INTERNET,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(deniedList, "${textPermission()}", "OK", "Cancel")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                } else {
                    //Toast.makeText(binding.root.context, "These permissions are denied: $deniedList", Toast.LENGTH_LONG).show()
                }
            }

        val navController = Navigation.findNavController(this, R.id.my_nav_host_fragment)


        binding.includeMain.menuIcons.setOnClickListener {
            binding.drawerLayout.open()

        }

        binding.includeMain.languageIcons.setOnClickListener {
            val alertDialog = AlertDialog.Builder(binding.root.context)
            val dialog = alertDialog.create()
            val bindingLanguage =
                DialogLanguageBinding.inflate(LayoutInflater.from(applicationContext))
            dialog.setView(bindingLanguage.root)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(true)

            when (Cache.til) {
                "uz" -> {
                    bindingLanguage.ilovaTiliTv.text = "Ilova Tili"
                    bindingLanguage.ilovaTilniTanlashTv.text =
                        "Dastur tilini o'zgartirish uchun tanlang"
                }
                "ru" -> {
                    bindingLanguage.ilovaTiliTv.text = "Язык приложения"
                    bindingLanguage.ilovaTilniTanlashTv.text =
                        "Выберите, чтобы изменить язык программы"
                }
                "krill" -> {
                    bindingLanguage.ilovaTiliTv.text = "Илова Тили"
                    bindingLanguage.ilovaTilniTanlashTv.text =
                        "Дастур тилини ўзгартириш учун танланг"
                }
            }


            bindingLanguage.uzbTv.setOnClickListener {
                Cache.til = "uz"
                checkLanguage()
                navController.navigate(R.id.homeFragment)
                dialog.dismiss()
            }

            bindingLanguage.rusTv.setOnClickListener {
                Cache.til = "ru"
                checkLanguage()
                navController.navigate(R.id.homeFragment)
                dialog.dismiss()
            }

            bindingLanguage.krillTv.setOnClickListener {
                Cache.til = "krill"
                checkLanguage()
                navController.navigate(R.id.homeFragment)
                dialog.dismiss()
            }

            dialog.show()


        }


        /*
        Cache.position = "0"
        navController.popBackStack(R.id.homeFragment, true)
        navController.navigate(R.id.homeFragment)
        */

        binding.navMainPage.setOnClickListener {
            navController.navigate(R.id.homeFragment)
            binding.drawerLayout.closeDrawers()
        }

        binding.navContentFirst.setOnClickListener {
            navController.navigate(R.id.content1Fragment)
            binding.drawerLayout.closeDrawers()
        }
        binding.navContentSecond.setOnClickListener {
            navController.navigate(R.id.content2Fragment)
            binding.drawerLayout.closeDrawers()
        }
        binding.navContentTherd.setOnClickListener {
            navController.navigate(R.id.content3Fragment)
            binding.drawerLayout.closeDrawers()
        }

        binding.navAuthors.setOnClickListener {
            navController.navigate(R.id.authorFragment)
            binding.drawerLayout.closeDrawers()
        }

        binding.navElevation.setOnClickListener {
            try {
                val uri: Uri = Uri.parse("market://details?id=$packageName")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                val uri: Uri =
                    Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }

            binding.drawerLayout.closeDrawers()
        }
        binding.navShare.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_SUBJECT, "Bardam bo'l")
                val shareMessage: String =
                    "https://play.google.com/store/apps/details?id=" + packageName
                intent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(intent, "share by"))
            } catch (e: Exception) {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            }
            binding.drawerLayout.closeDrawers()
        }
        binding.navCertificat.setOnClickListener {
            navController.navigate(R.id.certificatFragment)
            binding.drawerLayout.closeDrawers()
        }

        binding.navExit.setOnClickListener {
            finish()
            binding.drawerLayout.closeDrawers()
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.my_nav_host_fragment).navigateUp()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else
            super.onBackPressed()
    }

    private fun textPermission(): String {
        var textPer = ""
        when (Cache.til) {
            "uz" -> {
                textPer = "Ma'lumotlarni yuklash uchun ruxsat bering"
            }
            "ru" -> {
                textPer = "Разрешить загрузку данных"
            }
            "krill" -> {
                textPer = "Маълумотларни юклаш учун рухсат беринг"
            }
        }

        return textPer
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


        binding.navHeaderTitle.text = "Ekstremal vaziyatlarda o'zini boshqarish!"

        binding.navMainPageTitle.text = "Asosiy sahifa"

        binding.navContentFirstTitle.text = "Ekstremal vaziyatlarni boshqarish"
        binding.navContentSecondTitle.text = "O'ziga-o'zi birinchi yordam ko'rsatish"
        binding.navContentTherdTitle.text = "Bolaga g'amxo'rlik. Bolaga Yordam ko'rsatish"

        binding.navAuthorsTitle.text = "Ilova Haqida"

        binding.navElevationTitle.text = "Baholash"
        binding.navShareTitle.text = "Yuborish"

        binding.navCertificatTitle.text = "Guvohnoma"

        binding.navExitTitle.text = "Chiqish"


    }

    private fun loadKrillData() {
        binding.navHeaderTitle.text = "Экстремал вазиятларда узини бошкариш!"

        binding.navMainPageTitle.text = "Асосий саҳифа"

        binding.navContentFirstTitle.text = "Экстремал вазиятларни бошқариш"
        binding.navContentSecondTitle.text = "Ўзига-ўзи биринчи ёрдам кўрсатиш"
        binding.navContentTherdTitle.text = "Болага ғамхўрлик. Болага Ёрдам кўрсатиш"

        binding.navAuthorsTitle.text = "Илова Ҳақида"

        binding.navElevationTitle.text = "Баҳолаш"
        binding.navShareTitle.text = "Юбориш"

        binding.navCertificatTitle.text = "Гувоҳнома"

        binding.navExitTitle.text = "Чиқиш"


    }

    private fun loadRuData() {
        binding.navHeaderTitle.text = "Что делать во время экстремальных ситуаций!"

        binding.navMainPageTitle.text = "Главная страница"

        binding.navContentFirstTitle.text = "Управление экстремальными ситуациями"
        binding.navContentSecondTitle.text = "Оказание первой доврачебной"
        binding.navContentTherdTitle.text = "Забота о ребенке. Оказание помощи ребенку"

        binding.navAuthorsTitle.text = "О приложении"

        binding.navElevationTitle.text = "Оценка"
        binding.navShareTitle.text = "Поделиться"

        binding.navCertificatTitle.text = "Сертификат"

        binding.navExitTitle.text = "Выход"


    }


}