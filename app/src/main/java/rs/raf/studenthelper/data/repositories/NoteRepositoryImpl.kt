package rs.raf.studenthelper.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.studenthelper.data.datasources.local.NoteDao
import rs.raf.studenthelper.data.models.NoteEntity
import rs.raf.studenthelper.data.models.NoteUI

class NoteRepositoryImpl (
    private val localDataSource: NoteDao ): NoteRepository {

    override fun insert(note: NoteUI): Completable {
        val noteEntity = NoteEntity(note.id, note.title, note.content)
        return localDataSource
            .insert(noteEntity)
    }

    override fun delete(note: NoteUI): Completable {
        val noteEntity = NoteEntity(note.id, note.title, note.content)
        return localDataSource
            .delete(noteEntity)
    }

    override fun getAll(): Observable<List<NoteUI>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    //trebalo bi i ovo wrapovati (iako znam da cu podatke uvek dobiti iz baze)
                    NoteUI(it.id, it.title, it.content)
                }
            }
    }

    override fun update(note: NoteUI): Completable {
        val noteEntity = NoteEntity(note.id, note.title, note.content)
        return localDataSource
            .update(noteEntity)
    }

    override fun getAllWithFilter(filter: String): Observable<List<NoteUI>> {
        return localDataSource
            .getByFilter(filter)
            .map {
                it.map {
                    NoteUI(it.id, it.title, it.content)
                }
            }
    }
}