package rs.raf.studenthelper.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.studenthelper.data.models.SubjectUI

class SubjectDiffCallback : DiffUtil.ItemCallback<SubjectUI>() {

    override fun areItemsTheSame(oldItem: SubjectUI, newItem: SubjectUI): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SubjectUI, newItem: SubjectUI): Boolean {
        return oldItem.subject == newItem.subject && oldItem.tip == newItem.tip
                && oldItem.professor == newItem.professor && oldItem.classroom == newItem.classroom
                && oldItem.term == newItem.term
    }

}