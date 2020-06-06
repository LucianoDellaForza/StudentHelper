package rs.raf.studenthelper.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.studenthelper.data.models.NoteEntity

@Dao
abstract class NoteDao {

    //insert update delete get

    //add new note
    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: NoteEntity): Completable

    //izmeni
    @Update
    abstract fun update(entity: NoteEntity): Completable

    //izbrisi
    @Delete
    abstract fun delete(entity: NoteEntity): Completable

    //get all
    @Query("SELECT * FROM notes")
    abstract fun getAll(): Observable<List<NoteEntity>>


}