package rs.raf.studenthelper.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.studenthelper.data.models.NoteUI

class NoteDiffCallback : DiffUtil.ItemCallback<NoteUI>() {

    override fun areItemsTheSame(oldItem: NoteUI, newItem: NoteUI): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteUI, newItem: NoteUI): Boolean {
        return oldItem.title == newItem.title && oldItem.content == newItem.content
    }
}