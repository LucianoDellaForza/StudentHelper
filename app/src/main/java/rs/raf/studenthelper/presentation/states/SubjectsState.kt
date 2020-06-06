package rs.raf.studenthelper.presentation.states

import rs.raf.studenthelper.data.models.SubjectUI

//INFO - svaki activiti,fragment treba da ima zasebno svoje stanje

//OVA KLASA WRAPUJE SubjectUI klasu!!! (tj. SubjectUI se nalazi u njoj (pri Successu samo, logicno))

//ovo su stanja za ClassScheduleActivity
sealed class SubjectsState {
    object Loading: SubjectsState()
    object DataFetched: SubjectsState()
    data class Success(val subjects: List<SubjectUI>): SubjectsState()
    data class Error(val message: String): SubjectsState()
}