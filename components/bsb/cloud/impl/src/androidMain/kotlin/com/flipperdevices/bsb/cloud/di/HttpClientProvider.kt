package com.flipperdevices.bsb.cloud.di

import io.ktor.client.engine.cio.CIO

actual fun httpEngine() = CIO.create()