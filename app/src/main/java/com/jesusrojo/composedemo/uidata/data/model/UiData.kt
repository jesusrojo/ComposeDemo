package com.jesusrojo.composedemo.uidata.data.model

import androidx.annotation.Keep
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jesusrojo.composedemo.R
import java.util.*



@Keep
@Entity(tableName = "uidata_table")
data class UiData(
    val title: String,
    val icon: UiDataIcon = UiDataIcon.Default,
  //  val idData: UUID = UUID.randomUUID()//give them each a unique ID
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

enum class UiDataIcon(val imageVector: ImageVector, @StringRes val contentDescription: Int) {
    Square(Icons.Default.CropSquare, R.string.cd_crop_square),
    Done(Icons.Default.Done, R.string.cd_done),
    Event(Icons.Default.Event, R.string.cd_event),
    Privacy(Icons.Default.PrivacyTip, R.string.cd_privacy),
    Trash(Icons.Default.RestoreFromTrash, R.string.cd_restore);

    companion object {
        val Default = Square
    }
}