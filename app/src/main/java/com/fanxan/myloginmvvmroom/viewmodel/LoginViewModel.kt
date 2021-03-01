package com.fanxan.myloginmvvmroom.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fanxan.myloginmvvmroom.model.LoginTableModel
import com.fanxan.myloginmvvmroom.repository.LoginRepository
import kotlinx.coroutines.InternalCoroutinesApi

class LoginViewModel:ViewModel() {
    var liveDataLogin: LiveData<LoginTableModel>?=null

    @InternalCoroutinesApi
    fun insertData(context: Context, username:String, password:String){
        LoginRepository.insertData(context, username, password)
    }
    @InternalCoroutinesApi
    fun getLoginDetails(context: Context,username: String):LiveData<LoginTableModel>?{
        liveDataLogin = LoginRepository.getLoginDetails(context, username)
        return liveDataLogin
    }

}