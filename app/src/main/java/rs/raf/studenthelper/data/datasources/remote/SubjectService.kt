package rs.raf.studenthelper.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import rs.raf.studenthelper.data.models.SubjectResponse

interface SubjectService {

    @GET("raspored/json.php")
    fun getAll(): Observable<List<SubjectResponse>>
}