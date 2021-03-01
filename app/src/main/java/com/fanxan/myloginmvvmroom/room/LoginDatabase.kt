package com.fanxan.myloginmvvmroom.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fanxan.myloginmvvmroom.model.LoginTableModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = arrayOf(LoginTableModel::class),version = 1,exportSchema = false)
abstract class LoginDatabase :RoomDatabase() {
    abstract fun loginDao():DAOAccess

    companion object{
        @Volatile
        private var INSTANCE:LoginDatabase?=null

        @InternalCoroutinesApi
        fun getDatabaseClient(context:Context) :LoginDatabase{
            if(INSTANCE !=null) return INSTANCE !!
            synchronized(this){
                INSTANCE = Room.databaseBuilder(context,LoginDatabase::class.java,"Login Database")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE !!
            }

        }
    }
}