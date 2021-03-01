package com.fanxan.myloginmvvmroom.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.fanxan.myloginmvvmroom.model.LoginTableModel
import com.fanxan.myloginmvvmroom.room.LoginDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class LoginRepository {
    companion object{
        var loginDatabase : LoginDatabase?=null
        var loginTableModel:LiveData<LoginTableModel>?=null

        @InternalCoroutinesApi
        fun initializeDB(context: Context):LoginDatabase{
            return LoginDatabase.getDatabaseClient(context)
        }

        @InternalCoroutinesApi
        fun insertData(context: Context, username:String, password:String){
            loginDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                val loginDetails = LoginTableModel(username,password)
                loginDatabase!!.loginDao().InsertData(loginDetails)
            }
        }
        @InternalCoroutinesApi
        fun getLoginDetails(context: Context, username: String):LiveData<LoginTableModel>?{
            loginDatabase  = initializeDB(context)
            loginTableModel = loginDatabase!!.loginDao().getLoginDetails(username)

            return loginTableModel
        }

    }
}