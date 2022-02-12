package uz.hamroev.bardambolnew.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filepath")
class FileEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var file_name: String = ""
    var file_path: String = ""
}