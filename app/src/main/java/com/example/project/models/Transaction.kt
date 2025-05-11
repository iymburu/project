package com.example.project.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id : Int =0,
    val amount: Double,
    val date : String,
    val Category:String,
    val Type: String="income"
){
constructor(amount: Double , Type:String,Category:String,date:String) :this(0,amount,Type,Category,date)}
