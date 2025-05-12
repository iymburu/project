package com.example.project.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project.Dao.TransactionDao
import com.example.project.models.Transaction
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TransactionModel @Inject constructor(private val repository: TransactionRepository): ViewModel() {
    val allTransactions = repository.allTransactions
    val balance= repository.currentBalance

    fun addTransaction(amount: Double, Type:String, Category: String, date:String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val transaction= Transaction(
                    amount = amount,
                    Type = Type,
                    Category = Category,
                    date = date
                )
                repository.addTransaction(transaction)


            }
            catch (e: Exception){
                ("Transaction Error")
            }

        }
    }




}

class TransactionRepository @Inject constructor(private val transactionDao: TransactionDao):ViewModel() {
    val allTransactions: Flow<List<Transaction>> = transactionDao.getAllTransactions().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )
    val currentBalance: Flow<Double> =transactionDao.getCurrentBalance().stateIn( scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0.0)

    suspend fun addTransaction(transaction: Transaction){
        transactionDao.insert(transaction)
        }
    }


class TransactionModelFactory(
    private val repository: TransactionRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionModel::class.java)){

            return TransactionModel(repository) as T

        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }}