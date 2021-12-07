package com.anahid.todo.di

import android.content.Context
import androidx.room.Room
import com.anahid.todo.data.ToDoDB
import com.anahid.todo.data.ToDoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {

    @Provides
    fun provideToDoDb(@ApplicationContext context: Context): ToDoDao =
        Room.databaseBuilder(
            context,
            ToDoDB::class.java, "todo_db"
        ).build()
}
}
