package rs.raf.studenthelper.data.models

import java.util.*

data class SubjectUI (
    val id: Long,
    val subject: String,
    val tip: String,
    val professor: String,
    //val groups: List<String>,
    //val day: Date,
    val term: String,
    val classroom: String
)
