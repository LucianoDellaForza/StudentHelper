package rs.raf.studenthelper.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.studenthelper.R
import rs.raf.studenthelper.data.models.NoteUI
import rs.raf.studenthelper.presentation.view.recycler.diff.NoteDiffCallback
import rs.raf.studenthelper.presentation.view.recycler.viewholder.NoteViewHolder

class NoteAdapter(noteDiffCallback: NoteDiffCallback,
    private val onMenuClick: (NoteUI, Int) -> Unit) : ListAdapter<NoteUI, NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.layout_item_note, parent, false)
        return NoteViewHolder(view) { position, btnType ->
            val note = getItem(position)
            onMenuClick.invoke(note, btnType)
        }
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}