package rs.raf.studenthelper.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.studenthelper.data.datasources.local.converters.DateConverter
import rs.raf.studenthelper.data.datasources.local.converters.StringListConverter
import rs.raf.studenthelper.data.models.NoteEntity
import rs.raf.studenthelper.data.models.SubjectEntity

@Database(
    entities = [SubjectEntity::class, NoteEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters(StringListConverter::class, DateConverter::class)
abstract class DataBase : RoomDatabase() {
    //Getteri za sve DAO-e
    abstract fun getSubjectDao(): SubjectDao
    abstract fun getNoteDao(): NoteDao
}