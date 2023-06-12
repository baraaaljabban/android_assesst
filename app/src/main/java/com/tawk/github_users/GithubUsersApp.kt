package com.tawk.github_users

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.greenrobot.eventbus.EventBus

@HiltAndroidApp
class GithubUsersApp : Application()   {
    override fun onCreate() {
        EventBus.builder().installDefaultEventBus()
        super.onCreate()
    }
}