package com.abhiram.agrocare.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize

@VersionedParcelize
@Entity(tableName = "devices")
data class Devices(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "temp")
    var temp : Int,

    @ColumnInfo(name = "moisture")
    var moisture : Int,

    @ColumnInfo(name = "water")
    var water : Int
)
