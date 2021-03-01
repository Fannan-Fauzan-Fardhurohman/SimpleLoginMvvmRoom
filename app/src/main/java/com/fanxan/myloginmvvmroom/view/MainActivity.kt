package com.fanxan.myloginmvvmroom.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fanxan.myloginmvvmroom.R
import com.fanxan.myloginmvvmroom.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.InternalCoroutinesApi

class MainActivity : AppCompatActivity() {
    lateinit var loginViewModel: LoginViewModel
    lateinit var context: Context
    lateinit var strUsername:String
    lateinit var strPassword:String


    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        btnAddLogin.setOnClickListener {
            strUsername = txtUsername.text.toString().trim()
            strPassword = txtPassword.text.toString().trim()

            if(strUsername.isEmpty()){
                txtUsername.error = "Masukkan Username terlebih dahulu"
            }else if(strPassword.isEmpty()){
                txtPassword.error = "Masukkan Password terlebih dahulu"
            }else{
                loginViewModel.insertData(context, strUsername,strPassword)
                lblInsertResponse.text = "Insert Berhasil"
            }
        }
        btnReadLogin.setOnClickListener {
            strUsername = txtUsername.text.toString().trim()
            loginViewModel.getLoginDetails(context,strUsername)!!.observe(this, Observer {
                if(it == null){
                    lblReadResponse.text = "Data Tidak ditemukan"
                    lblUseraname.text = "- - -"
                    lblPassword.text = "- - -"
                }else{
                    lblUseraname.text = it.Username
                    lblPassword.text = it.Password

                    lblReadResponse.text = "Data berhasil ditemukan"
                }
            })
        }
    }
}