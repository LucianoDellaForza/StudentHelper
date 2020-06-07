package rs.raf.studenthelper.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.studenthelper.data.models.NoteUI

interface NoteContract {

    interface ViewModel {
        val notes: LiveData<List<NoteUI>>

        fun getAllNotes()
        fun deleteNote(note: NoteUI)
        fun updateNote(note: NoteUI)
        fun insertNote(note: NoteUI)
        fun getNotesWithFilter(filter: String)
    }



}