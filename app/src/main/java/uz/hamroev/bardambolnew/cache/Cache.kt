package uz.hamroev.bardambolnew.cache

import android.content.Context
import android.content.SharedPreferences

object Cache {

    private const val NAME = "language"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context){
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit){
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var til:String?
        get() = sharedPreferences.getString("til1","ru")
        set(value) = sharedPreferences.edit() {
            if (value != null){
                it.putString("til1",value)
            }
        }

    var position:String?
        get() = sharedPreferences.getString("joy","0")
        set(value) = sharedPreferences.edit() {
            if (value != null){
                it.putString("joy",value)
            }
        }

    //mavzular uchun cache


    var turmushMavzu:Int?
        get() = sharedPreferences.getInt("turmush",0)
        set(value) = sharedPreferences.edit() {
            if (value != null){
                it.putInt("turmush",value)
            }
        }

    var bolaTarbiyasiMavzu:Int?
        get() = sharedPreferences.getInt("tarbiya",0)
        set(value) = sharedPreferences.edit() {
            if (value != null){
                it.putInt("tarbiya",value)
            }
        }

    var BirinchiYordamMavzu:Int?
        get() = sharedPreferences.getInt("birinchiyordam",0)
        set(value) = sharedPreferences.edit() {
            if (value != null){
                it.putInt("birinchiyordam",value)
            }
        }


    //mavzular uchun cache

    var main3MavzuPosition:Int?
        get() = sharedPreferences.getInt("main3Mavzu",0)
        set(value) = sharedPreferences.edit() {
            if (value != null){
                it.putInt("main3Mavzu",value)
            }
        }

}
