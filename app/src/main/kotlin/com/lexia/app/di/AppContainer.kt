package com.lexia.app.di

import android.content.Context

/**
 * Manual dependency injection container for the Lexia app.
 *
 * This container exposes the dependencies required by the app and is owned by
 * the [android.app.Application] class, matching the lifecycle of the app process.
 * It is intentionally lean for the MVP phase and can be migrated to Hilt later.
 */
class AppContainer(private val applicationContext: Context) {
    // TODO: initialize repositories and data sources here as the app grows.
}
