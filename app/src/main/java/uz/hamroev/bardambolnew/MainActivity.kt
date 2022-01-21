package uz.hamroev.bardambolnew

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import uz.hamroev.bardambolnew.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        /* off dark mode*/
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)


        // this is code for full Screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        // this is code for ActionBar hide
        supportActionBar?.hide()

        //this is code animation typeWriter

        startAnimation()

        binding.introTv.text = ""
        binding.introTv.animateText("Bardam \nBo'l")
        binding.introTv.setCharacterDeley(80)

        startAnimation()

        Handler(Looper.getMainLooper()).postDelayed({


        }, 2500)

    }

    private fun startAnimation() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.anim_intro)
        binding.introIv.startAnimation(anim)
    }
}