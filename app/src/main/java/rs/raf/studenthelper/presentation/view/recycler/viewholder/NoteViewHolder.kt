package rs.raf.studenthelper.presentation.view.recycler.viewholder

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_item_note.*
import rs.raf.studenthelper.data.models.NoteUI

class NoteViewHolder (
    override val containerView: View,
    onBtnClick: (Int, Int) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        deleteBtn.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onBtnClick.invoke(adapterPosition, 1)
            }
        }

        editBtn.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onBtnClick.invoke(adapterPosition, 2)
            }
        }

        archiveBtn.setOnClickListener {
//            Toast.makeText(this, "Not implemented", Toast.LENGTH_SHORT).show()
        }
    }

    fun bind(note: NoteUI) {
        titleTv.text = note.title
        contentTv.text = note.content
    }
}