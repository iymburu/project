package com.example.project.data

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.project.models.User
import com.example.project.navigation.ROUTE_HOME
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel (var navController: NavController, var context: Context){
    var mAuth : FirebaseAuth
    init {
        mAuth= FirebaseAuth.getInstance()
    }
    fun Signup(email :String,username:String,pass: String, confpass :String){
      if  (email.isBlank()||username.isBlank()||pass.isBlank()||confpass.isBlank()){
            Toast.makeText(context,"Enter all of the required details",Toast.LENGTH_LONG).show()
          return}
        else if (pass!=confpass){
            Toast.makeText(context,"Passwords do not match",Toast.LENGTH_LONG).show()
        }
        else{
            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                if (it.isSuccessful){
                    navController.navigate(ROUTE_HOME)
                    val userData= User(email,username,pass,mAuth.currentUser!!.uid)
                    val regRef= FirebaseDatabase.getInstance().getReference()
                        .child("Users/"+mAuth.currentUser!!.uid)
                    regRef.setValue(userData).addOnCompleteListener{
                        if (it.isSuccessful) {
                            Toast.makeText(context,"Account created successfully",Toast.LENGTH_LONG).show()
                            navController.navigate(ROUTE_HOME)

                        }
                    }
                }
            }
        }
        }
    }

