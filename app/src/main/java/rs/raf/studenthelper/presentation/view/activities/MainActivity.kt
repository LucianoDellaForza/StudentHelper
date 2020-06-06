package rs.raf.studenthelper.presentation.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.studenthelper.R
import rs.raf.studenthelper.presentation.contract.SubjectContract
import rs.raf.studenthelper.presentation.viewmodel.SubjectViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val subjectViewModel: SubjectContract.ViewModel by viewModel<SubjectViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initListeners()
    }

    private fun initListeners() {
        scheduleBtn.setOnClickListener {
            val intent = Intent(this, ClassScheduleActivity::class.java)
            startActivity(intent)
        }

        notesBtn.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }



    }
}
