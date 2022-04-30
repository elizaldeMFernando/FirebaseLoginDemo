package com.example.firebaselogindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    lateinit var mauth:FirebaseAuth 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mauth = FirebaseAuth.getInstance()
        findViewById<Button>(R.id.loginButton).setOnClickListener {
            login()
        }
    }

    override fun onStart() {
        super.onStart()
        val user:FirebaseUser? = mauth.currentUser
        if(user==null){
            //mostra login
            Toast.makeText(this,"Mostrando login",Toast.LENGTH_LONG).show()
        }else{
            //No mostrar login
            Toast.makeText(this,"Mostrando Menu",Toast.LENGTH_LONG).show()

        }

    }
    fun login(){
        val user:String=findViewById<EditText>(R.id.userTxt).text.toString()
        val password:String=findViewById<EditText>(R.id.passwordTxt).text.toString()

        mauth.signInWithEmailAndPassword(user,password).addOnCompleteListener {
            if(it.isSuccessful){
                Log.d("Login","Login Exitoso")

                findViewById<TextView>(R.id.textView).text = "bienvenido"
            }
            else{
                Log.d("Login","Login Error")

                //Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
                findViewById<TextView>(R.id.textView).text = "Error"
            }
        }
    }


}