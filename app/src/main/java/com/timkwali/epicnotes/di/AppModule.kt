package com.timkwali.epicnotes.di

import android.app.Application
import androidx.room.Room
import com.timkwali.epicnotes.data.localdata.TasksDatabase
import com.timkwali.epicnotes.data.repository.RepositoryImpl
import com.timkwali.epicnotes.domain.repository.Repository
import com.timkwali.epicnotes.domain.usecase.*
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

    @Provides
    @Singleton
    fun provideRepository(db: TasksDatabase): Repository {
        return RepositoryImpl(db)
    }

    @Provides
    @Singleton
    fun provideTaskUseCases(repository: Repository): TaskUseCases {
        return TaskUseCases(
            getAllTasksUseCase = GetAllTasksUseCase(repository),
            saveTaskUseCase = SaveTaskUseCase(repository),
            updateTaskUseCase = UpdateTaskUseCase(repository),
            getOldestTasksUseCase = GetOldestTasksUseCase(repository)
        )
    }
}