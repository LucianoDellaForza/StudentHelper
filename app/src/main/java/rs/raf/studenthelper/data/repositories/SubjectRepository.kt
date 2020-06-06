package rs.raf.studenthelper.data.repositories

import io.reactivex.Observable
import rs.raf.studenthelper.data.models.Resource
import rs.raf.studenthelper.data.models.SubjectUI

interface SubjectRepository {

    //fetchuje sa neta (i insertuje)
    fun fetchAll(): Observable<Resource<Unit>>

    //getuje iz baze
    fun getAll(): Observable<List<SubjectUI>>

    //getuje iz baze sa filterom
    fun getByFilter(filter: String): Observable<List<SubjectUI>>
}