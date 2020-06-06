package rs.raf.studenthelper.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity (

    @PrimaryKey(autoGenerate = true) var id: Long,
    var title: String,
    var content: String
)