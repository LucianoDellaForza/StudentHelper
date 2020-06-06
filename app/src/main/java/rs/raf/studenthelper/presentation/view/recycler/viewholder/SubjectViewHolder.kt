package rs.raf.studenthelper.presentation.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_item_subject.*
import rs.raf.studenthelper.data.models.SubjectUI

class SubjectViewHolder (override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(subject: SubjectUI) {
        subjectTv.text = subject.subject
        typeTv.text = subject.tip
        professorTv.text = subject.professor
        classroomTv.text = subject.classroom
        groupsTv.text = subject.term
    }
}
