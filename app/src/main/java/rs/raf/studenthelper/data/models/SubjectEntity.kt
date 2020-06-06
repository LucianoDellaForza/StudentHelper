package rs.raf.studenthelper.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.util.*

@Entity(tableName = "subjects")
data class SubjectEntity (

    @PrimaryKey(autoGenerate = true) var id: Long,
    var subject: String,
    var tip: String,
    var professor: String,
    //INFO - Room ne zna kako da obradi kompleksne tipove (kao sto su Date, List<String>...) i zbog toga se prave konvertori u StudentDatabase
    //i dodaju se tamo u @TypeConverters()
    //var groups: List<String>, //= listOf()
    //var day: Date,  //= Date()
    var term: String,
    var classroom: String
)