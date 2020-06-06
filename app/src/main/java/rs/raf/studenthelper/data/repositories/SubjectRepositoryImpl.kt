package rs.raf.studenthelper.data.repositories

import io.reactivex.Observable
import rs.raf.studenthelper.data.datasources.local.SubjectDao
import rs.raf.studenthelper.data.datasources.remote.SubjectService
import rs.raf.studenthelper.data.models.Resource
import rs.raf.studenthelper.data.models.SubjectEntity
import rs.raf.studenthelper.data.models.SubjectUI
import timber.log.Timber

class SubjectRepositoryImpl(
    private val localDataSource: SubjectDao,
    private val remoteDataSource: SubjectService): SubjectRepository {

    //skida sa neta (i insertuje u bazu)
    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll()
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.map {
                    SubjectEntity(
                        0,  //sta god da prosledim, auto generise se
                        it.subject,
                        it.type,
                        it.professor,
                        //it.groups,
                        //it.day,
                        it.term,
                        it.classroom
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    //getuje iz baze
    override fun getAll(): Observable<List<SubjectUI>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    //trebalo bi i ovo wrapovati (iako znam da cu podatke uvek dobiti iz baze)
                    SubjectUI(it.id, it.subject, it.tip, it.professor, it.term, it.classroom) //it.professor, it.groups, it.day
                }
            }
    }

    //getuje iz baze sa filterom
    override fun getByFilter(filter: String): Observable<List<SubjectUI>> {
        return localDataSource
            .getByFilter(filter)
            .map {
                it.map {
                    SubjectUI(it.id, it.subject, it.tip, it.professor, it.term, it.classroom)   //it.professor, it.groups, it.day
                }
            }
    }

}