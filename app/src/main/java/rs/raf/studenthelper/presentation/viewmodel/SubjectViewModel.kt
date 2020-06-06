package rs.raf.studenthelper.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.studenthelper.data.models.Resource
import rs.raf.studenthelper.data.models.SubjectUI
import rs.raf.studenthelper.data.repositories.SubjectRepository
import rs.raf.studenthelper.presentation.contract.SubjectContract
import rs.raf.studenthelper.presentation.states.SubjectsState
import timber.log.Timber
import java.util.*

class SubjectViewModel (
    private val subjectRepository: SubjectRepository
) : ViewModel(), SubjectContract.ViewModel {

    private val subscriptions = CompositeDisposable()

    override val subjectsState: MutableLiveData<SubjectsState> = MutableLiveData()

    //za filtriranje
    private val publishSubject: PublishSubject<String> = PublishSubject.create()    //String treba TipZaFilter

    init {
        //za filtriranje publish subject implementacija
    }



    override fun fetchAllSubjects() {
        val subscription = subjectRepository
            .fetchAll()
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("fetchAllSubjects()")
                    when(it) {
                        is Resource.Loading -> subjectsState.value = SubjectsState.Loading
                        is Resource.Success -> subjectsState.value = SubjectsState.DataFetched
                        is Resource.Error -> subjectsState.value = SubjectsState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    subjectsState.value = SubjectsState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllSubjects() {
        val subscription = subjectRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("getAllSubjects()")
                    subjectsState.value = SubjectsState.Success(it)
                },
                {
                    subjectsState.value = SubjectsState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getSubjectsByFilter(
        filter: String
    ) {
        // publishSubject.onNext(filter)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}