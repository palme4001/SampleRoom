package de.sample.room.activity.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "books",
        foreignKeys = [
            ForeignKey( entity = Author::class,
                        parentColumns = ["id"],
                        childColumns = ["author_id"],
                        onDelete = CASCADE)
        ])
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "author_id") val authorId: Int,
    @ColumnInfo(name = "url_index") val urlIndex: Int = -1)