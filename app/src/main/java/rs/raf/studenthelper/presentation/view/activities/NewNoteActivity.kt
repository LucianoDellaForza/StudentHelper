package rs.raf.studenthelper.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_new_note.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.studenthelper.R
import rs.raf.studenthelper.data.models.NoteUI
import rs.raf.studenthelper.presentation.contract.NoteContract
import rs.raf.studenthelper.presentation.viewmodel.NoteViewModel

class NewNoteActivity : AppCompatActivity(R.layout.activity_new_note) {

    private val noteViewModel: NoteContract.ViewModel by viewModel<NoteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        abortBtn.setOnClickListener {
            finish()
        }

        changeNoteBtn.setOnClickListener {
            val title = newTitleEt.text.toString()
            val content = newContentEt.text.toString()
            noteViewModel.insertNote(NoteUI(0, title, content))
            finish()
        }
    }

    private fun initObservers() {

    }



}
