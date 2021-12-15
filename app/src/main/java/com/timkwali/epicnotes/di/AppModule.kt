package com.timkwali.epicnotes.di

import android.app.Application
import androidx.room.Room
import com.timkwali.epicnotes.data.localdata.TasksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTasksDatabase(app: Application): TasksDatabase {
        return Room.databaseBuilder(
            app,
            TasksDatabase::class.java,
            TasksDatabase.DATABASE_NAME
        ).build()
    }
}