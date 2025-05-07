package com.example.project.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id:Int?,
    val title: String,
    val amount: Double,
    val date : String,
    val Category:String,
    val Type: String
)
