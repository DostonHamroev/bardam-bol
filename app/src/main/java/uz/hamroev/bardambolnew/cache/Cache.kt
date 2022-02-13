package uz.hamroev.bardambolnew.cache

import android.content.Context
import android.content.SharedPreferences

object Cache {

    private const val NAME = "language"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var til: String?
        get() = sharedPreferences.getString("til1", "uz")
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putString("til1", value)
            }
        }

    var position: String?
        get() = sharedPreferences.getString("joy", "0")
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putString("joy", value)
            }
        }

    //mavzular uchun cache


    var path: String?
        get() = sharedPreferences.getString("turmush", "")
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putString("turmush", value)
            }
        }

    var musicPath1: String?
        get() = sharedPreferences.getString("turmush", "")
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putString("turmush", value)
            }
        }
    var musicPath2: String?
        get() = sharedPreferences.getString("turmush", "")
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putString("turmush", value)
            }
        }

    var musicPath3: String?
        get() = sharedPreferences.getString("turmush", "")
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putString("turmush", value)
            }
        }







}
