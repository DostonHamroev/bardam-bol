package uz.hamroev.bardambolnew.activity

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import uz.hamroev.bardambolnew.R
import uz.hamroev.bardambolnew.cache.Cache
import uz.hamroev.bardambolnew.databinding.ActivityHomeBinding
import uz.hamroev.bardambolnew.databinding.DialogLanguageBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Cache.init(this)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        supportActionBar?.hide()


        binding.includeMain.menuIcon.setOnClickListener {
            binding.drawerLayout.open()

        }

        binding.includeMain.languageIcon.setOnClickListener {
            val alertDialog = AlertDialog.Builder(binding.root.context)
            val dialog = alertDialog.create()
            val bindingLanguage =
                DialogLanguageBinding.inflate(LayoutInflater.from(applicationContext))
            dialog.setView(bindingLanguage.root)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(true)

            bindingLanguage.uzbTv.setOnClickListener {
                dialog.dismiss()
            }

            bindingLanguage.rusTv.setOnClickListener {
                dialog.dismiss()
            }

            bindingLanguage.krillTv.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()


        }

        val navController = Navigation.findNavController(this, R.id.my_nav_host_fragment)

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
            binding.drawerLayout.closeDrawers()
        }
        binding.navShare.setOnClickListener {
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
}