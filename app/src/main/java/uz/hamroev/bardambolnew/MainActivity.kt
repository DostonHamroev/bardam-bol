package uz.hamroev.bardambolnew

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import uz.hamroev.bardambolnew.activity.HomeActivity
import uz.hamroev.bardambolnew.databinding.ActivityMainBinding
import uz.hamroev.bardambolnew.room.FileDatabase
import uz.hamroev.bardambolnew.room.FileEntity

class MainActivity : AppCompatActivity() {


    lateinit var fileDatabase: FileDatabase

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        /* off dark mode*/
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(binding.root)

        FileDatabase.getInstance(this)

        // this is code for full Screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        startAnimation()

        // this is code for ActionBar hide
        supportActionBar?.hide()

        //this is code animation typeWriter

        //writeDatabase()


        binding.introTv.text = ""
        binding.introTv.animateText("Bardam \nBo'l")
        binding.introTv.setCharacterDeley(80)


        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()

        }, 3500)

    }

//    private fun writeDatabase() {
//        var fileEntity = FileEntity()
//        fileEntity.file_name = "image"
//        fileEntity.file_path = "mkdir/image"
//        fileEntity.is_download = "no"
//        fileDatabase.fileDao().addFile(fileEntity)
//    }

    private fun startAnimation() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.anim_intro)
        binding.introIv.startAnimation(anim)
    }
}