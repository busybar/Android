package com.flipperdevices.busybar.di

import io.ktor.client.engine.js.Js

actual fun httpEngine() = Js.create()