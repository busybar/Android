package com.flipperdevices.bsb.cloud.di

import io.ktor.client.engine.js.Js

actual fun httpEngine() = Js.create()