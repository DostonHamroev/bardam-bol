package uz.hamroev.bardambolnew.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloads")
class FileEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var file_name: String? = null
    var file_path: String? = null
    var is_download: String?
        get() = null
        set(value) = TODO()
}