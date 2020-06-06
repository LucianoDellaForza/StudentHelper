package rs.raf.studenthelper.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_update.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.studenthelper.R
import rs.raf.studenthelper.data.models.NoteUI
import rs.raf.studenthelper.presentation.contract.NoteContract
import rs.raf.studenthelper.presentation.viewmodel.NoteViewModel

class UpdateNoteActivity : AppCompatActivity(R.layout.activity_update) {

    private val noteViewModel: NoteContract.ViewModel by viewModel<NoteViewModel>()

    private var note: NoteUI? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        parseIntent()
        initListeners()
    }

    private fun parseIntent() {
        intent?.let {
            note = it.getParcelableExtra("noteKey")
            if (note != null) {
                newTitleEt2.setText(note!!.title)
                newContentEt2.setText(note!!.content)
            }
        }
    }

    private fun initListeners() {
        changeNoteBtn2.setOnClickListener {
            noteViewModel.updateNote(note!!)
            finish()
        }

        abortBtn2.setOnClickListener {
            finish()
        }
    }

}
