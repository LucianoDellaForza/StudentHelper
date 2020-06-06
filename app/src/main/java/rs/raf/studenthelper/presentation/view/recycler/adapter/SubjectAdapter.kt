package rs.raf.studenthelper.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.studenthelper.R
import rs.raf.studenthelper.data.models.SubjectUI
import rs.raf.studenthelper.presentation.view.recycler.diff.SubjectDiffCallback
import rs.raf.studenthelper.presentation.view.recycler.viewholder.SubjectViewHolder

class SubjectAdapter : ListAdapter<SubjectUI, SubjectViewHolder>(SubjectDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.layout_item_subject, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}