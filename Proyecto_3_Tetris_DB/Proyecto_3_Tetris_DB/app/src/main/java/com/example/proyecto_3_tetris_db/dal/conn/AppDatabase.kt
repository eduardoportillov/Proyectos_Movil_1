package com.example.proyecto_3_tetris_db.dal.conn

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.proyecto_3_tetris_db.dal.bll.PersonaDao
import com.example.proyecto_3_tetris_db.dal.models.Persona

@Database(entities = [Persona::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personaDao(): PersonaDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "personasdb"
                ).allowMainThreadQueries().build()
            }
            return INSTANCE!!
        }
    }
}