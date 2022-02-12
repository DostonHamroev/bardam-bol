package uz.hamroev.bardambolnew.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FileDao {

    @Insert
    fun addFilePathAndName(fileEntity: FileEntity)

    @Query("SELECT * FROM filepath WHERE file_name LIKE '%' || :file_name || '%'")
    fun searchFileName(file_name: String): List<FileEntity>
}