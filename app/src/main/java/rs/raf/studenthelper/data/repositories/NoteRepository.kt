package rs.raf.studenthelper.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.studenthelper.data.models.NoteUI

interface NoteRepository {

    //arhiviranje nove beleske
    fun insert(note: NoteUI): Completable

    //brisanje beleske
    fun delete(note: NoteUI): Completable

    //getovanje svih beleski
    fun getAll(): Observable<List<NoteUI>>

    //izmena beleske
    fun update(note: NoteUI): Completable

}