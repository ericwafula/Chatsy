package com.example.chatsy

import android.app.Application
import com.example.chatsy.di.AppModule
import com.example.data.di.DataModule
import com.example.datasource.local.di.LocalDatasourceModule
import com.example.datasource.remote.di.RemoteDatasourceModule
import com.example.presentation.auth.di.AuthPresentationModule
import com.example.presentation.chat.di.ChatPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class ChatsyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ChatsyApplication)
            androidLogger()
            modules(
                AuthPresentationModule,
                ChatPresentationModule,
                DataModule,
                RemoteDatasourceModule,
                LocalDatasourceModule,
                AppModule,
            )
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
