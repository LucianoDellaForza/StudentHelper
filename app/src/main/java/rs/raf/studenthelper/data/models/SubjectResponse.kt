package rs.raf.studenthelper.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*


//"predmet":"Matematicka analiza",
//"tip":"Predavanja",
//"nastavnik":"Jovanovic Irena",
//"grupe":"101, 102",
//"dan":"\u010cET",
//"termin":"11:15-13",
//"ucionica":"Raf 11"

@JsonClass(generateAdapter = true)
data class SubjectResponse(
    @Json(name = "predmet") val subject: String,
    @Json(name = "tip") val type: String,
    @Json(name = "nastavnik") val professor: String,
   // @Json(name = "grupe") val groups: List<String>,
    //@Json(name = "dan") val day: Date,
    @Json(name = "termin") val term: String,
    @Json(name = "ucionica") val classroom: String

    )
