package rs.raf.studenthelper.presentation.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_note.*
import kotlinx.android.synthetic.main.layout_item_note.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.studenthelper.R
import rs.raf.studenthelper.presentation.contract.NoteContract
import rs.raf.studenthelper.presentation.view.recycler.adapter.NoteAdapter
import rs.raf.studenthelper.presentation.viewmodel.NoteViewModel
import androidx.lifecycle.Observer
import rs.raf.studenthelper.data.models.NoteUI
import rs.raf.studenthelper.presentation.view.recycler.diff.NoteDiffCallback

class NoteActivity : AppCompatActivity(R.layout.activity_note) {

    private val noteViewModel: NoteContract.ViewModel by viewModel<NoteViewModel>()

    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        listRvNotes.layoutManager = LinearLayoutManager(this)
        adapter = NoteAdapter(NoteDiffCallback()) { note, btnType ->

            if (btnType == 1) {
                noteViewModel.deleteNote(note)
            } else if (btnType == 2) {
                val intent = Intent(this, UpdateNoteActivity::class.java)
                intent.putExtra("noteKey", note)
                startActivity(intent)
            } else {
                //archive not implemented
            }
        }
        listRvNotes.adapter = adapter
    }

    private fun initListeners() {

        inputEt.doAfterTextChanged {
            val filter = it.toString()
            noteViewModel.getNotesWithFilter(filter)
        }

        fab.setOnClickListener {
            val intent = Intent(this, NewNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initObservers() {
        noteViewModel.notes.observe(this, Observer {
            renderState(it)
        })
        noteViewModel.getAllNotes()
    }

    private fun renderState(notes: List<NoteUI>) {
        adapter.submitList(notes)
    }

}
