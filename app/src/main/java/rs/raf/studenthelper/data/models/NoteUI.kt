package rs.raf.studenthelper.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NoteUI (
    val id: Long,
    val title: String,
    val content: String
): Parcelable