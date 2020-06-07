package rs.raf.studenthelper.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.studenthelper.data.models.SubjectUI
import rs.raf.studenthelper.presentation.states.SubjectsState
import java.util.*

interface SubjectContract {

    interface ViewModel {

        val subjectsState: LiveData<SubjectsState>

        fun fetchAllSubjects()
        fun getAllSubjects()
        fun getSubjectsWithFilter(filter: String)

    }
}