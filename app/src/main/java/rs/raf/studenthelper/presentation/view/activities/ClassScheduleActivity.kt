package rs.raf.studenthelper.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_class_schedule.*
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.compat.SharedViewModelCompat.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.studenthelper.R
import rs.raf.studenthelper.presentation.contract.SubjectContract
import rs.raf.studenthelper.presentation.view.recycler.adapter.SubjectAdapter
import rs.raf.studenthelper.presentation.viewmodel.SubjectViewModel
import androidx.lifecycle.Observer
import rs.raf.studenthelper.presentation.states.SubjectsState

class ClassScheduleActivity : AppCompatActivity(R.layout.activity_class_schedule) {

    private val subjectViewModel: SubjectContract.ViewModel by viewModel<SubjectViewModel>()

    private lateinit var adapter: SubjectAdapter

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
        listRv.layoutManager = LinearLayoutManager(this)
        adapter = SubjectAdapter()
        listRv.adapter = adapter
    }

    private fun initListeners() {
        //filtriranje
    }


    private fun initObservers() {
        subjectViewModel.subjectsState.observe(this, Observer {
            renderState(it)
        })
        subjectViewModel.getAllSubjects()
        subjectViewModel.fetchAllSubjects()
    }

    private fun renderState(state: SubjectsState) {
        when (state) {
            is SubjectsState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.subjects)
            }
            is SubjectsState.Error -> {
                showLoadingState(false)
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is SubjectsState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(this, "Fresh data fetched from the server", Toast.LENGTH_SHORT).show()
            }
            is SubjectsState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        inputEt.isVisible = !loading
        listRv.isVisible = !loading
        searchBtn.isVisible = !loading
        loadingPb.isVisible = loading
    }

}
