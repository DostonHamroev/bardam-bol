package uz.hamroev.bardambolnew.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FileDao {

    @Query("SELECT * from downloads")
    fun getAllFilePath(): List<FileEntity>

    @Query("SELECT * FROM downloads WHERE file_name LIKE '%' || :fileName || '%' ")
    fun getSearchFileName(fileName: String): List<FileEntity>

    @Insert
    fun addFile(fileEntity: FileEntity)


}