package com.example.project.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.project.models.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: Transaction)

    @Query("SELECT * FROM transactions ORDER BY date Desc")
    fun getAllTransactions(): Flow<List<Transaction>>
    @Query("SELECT * FROM transactions WHERE Type = 'Expense' ORDER BY amount DESC LIMIT 5")
    fun getTopExpenses(): Flow<List<Transaction>>
    @Insert
    suspend fun insertExpense(transaction: Transaction)

    @Delete
    suspend fun deleteExpense(transaction: Transaction)

    @Update
    suspend fun updateExpense(transaction: Transaction)

    @Query(
        """
        SELECT COALESCE(
        SUM
        (CASE WHEN Type ="income" THEN  amount
        ELSE +amount END ),0.0)FROM transactions
   """
    )
    fun getCurrentBalance(): Flow<Double>
}