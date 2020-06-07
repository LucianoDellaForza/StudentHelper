package rs.raf.studenthelper.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.studenthelper.data.models.NoteUI
import rs.raf.studenthelper.data.repositories.NoteRepository
import rs.raf.studenthelper.presentation.contract.NoteContract
import timber.log.Timber
import java.util.concurrent.TimeUnit

class NoteViewModel(
    private val noteRepository: NoteRepository
) : ViewModel(), NoteContract.ViewModel {

    private val subscriptions = CompositeDisposable()

    override val notes: MutableLiveData<List<NoteUI>> = MutableLiveData()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {
        val contactsDisposable = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                noteRepository
                    .getAllWithFilter(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    notes.value = it
                },
                {
                    Timber.e("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(contactsDisposable)
    }

    override fun getAllNotes() {
        val subscription = noteRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Retrieved list of notes")
                    notes.value = it
                },
                {
                    Timber.e("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteNote(note: NoteUI) {
        val subscription = noteRepository
            .delete(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Note deleted")
                },
                {
                    Timber.e("Error happened while deleting note")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun updateNote(note: NoteUI) {
        val subscription = noteRepository
            .update(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Note updated")
                },
                {
                    Timber.e("Error happened while updating note")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun insertNote(note: NoteUI) {
        val subscription = noteRepository
            .insert(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Note inserted")
                },
                {
                    Timber.e("Error happened while inserting note")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getNotesWithFilter(filter: String) {
        publishSubject.onNext(filter)
    }


}