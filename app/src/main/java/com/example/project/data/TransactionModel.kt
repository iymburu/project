package com.example.project.data

import android.app.ProgressDialog
import android.content.Context
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import android.widget.Toast
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import com.example.project.models.Transaction
import com.example.project.navigation.ROUTE_LOGIN
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase


class TransactionModel(
    var navController: NavHostController,
    var context: Context
){
    var authRepository: AuthViewModel
    var progress: ProgressDialog

    init {
        authRepository = AuthViewModel(navController, context)
        if (!authRepository.isloggedin()) {
            navController.navigate(ROUTE_LOGIN)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }
    fun saveTransaction(category:String,amount :Double,type:String,selectedDate:String){
        var id = System.currentTimeMillis().toString()
        var total = 0.0
        var TransactionData = Transaction(amount, type,category,selectedDate, id)
        var TransactionRef = FirebaseDatabase.getInstance().getReference()
            .child("Transactions/$id")
        progress.show()
        TransactionRef.setValue(TransactionData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    fun deleteProduct(id: String) {
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("Transactions/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Transaction deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateTransaction(
        category:String,
        amount: Double,
        type:String,
        selectedDate:String,
        id: String) {
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("Transactions/$id")
        progress.show()
        var updateData = Transaction(amount,category,type,selectedDate , id)
        updateRef.setValue(updateData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun viewTransaction(
        transactions: SnapshotStateList<Transaction>
    ): SnapshotStateList<Transaction> {
        val ref = FirebaseDatabase.getInstance().getReference().child("Transactions")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                transactions.clear()
                for (snap in snapshot.children) {
                    val transaction = snap.getValue(Transaction::class.java)
                    transaction?.let{
                    transactions.add(it)}
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return transactions
    }

}