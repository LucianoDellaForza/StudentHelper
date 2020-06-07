package rs.raf.studenthelper.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.studenthelper.data.models.SubjectEntity

@Dao
abstract class SubjectDao {

    //INSERT AND DELETE
    @Transaction
    open fun deleteAndInsertAll(entities: List<SubjectEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<SubjectEntity>): Completable

    @Query("DELETE FROM subjects")
    abstract fun deleteAll()

    //GET ALL FROM BASE
    @Query("SELECT * FROM subjects")
    abstract fun getAll(): Observable<List<SubjectEntity>>

    //get with filter
    @Query("SELECT * FROM subjects WHERE professor LIKE :filter || '%' OR subject LIKE :filter || '%'")
    abstract fun getByFilter(filter: String): Observable<List<SubjectEntity>>


}