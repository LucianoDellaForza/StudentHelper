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

    //Filter po groups i day ako stignem
    @Query("SELECT * FROM subjects WHERE subject LIKE :filter || '%' OR professor LIKE :filter || '%'")
    abstract fun getByFilter(filter: String): Observable<List<SubjectEntity>>

}