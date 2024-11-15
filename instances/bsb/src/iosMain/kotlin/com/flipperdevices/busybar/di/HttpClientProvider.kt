package com.flipperdevices.busybar.di

import io.ktor.client.engine.cio.CIO

actual fun httpEngine() = CIO.create()