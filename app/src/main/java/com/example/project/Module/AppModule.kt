package com.example.project.Module

import android.content.Context
import androidx.room.Room
import com.example.project.Dao.TransactionDao
import com.example.project.data.AppDatabase
import com.example.project.data.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "Expense_db"
        ).fallbackToDestructiveMigration().build()
    }
    @Provides
    fun provideDao(database: AppDatabase):TransactionDao{
        return database.transactionDao()
    }
    @Provides
    fun provideRepository(transactionDao: TransactionDao): TransactionRepository {
        return TransactionRepository(transactionDao)
    }
}