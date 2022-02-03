package uz.hamroev.bardambolnew.app

import android.app.Application
import uz.hamroev.bardambolnew.room.FileDatabase

class App: Application() {

    override fun onCreate() {
        FileDatabase.getInstance(this)
        super.onCreate()
    }
}