package com.flipperdevices.bsb.di

import io.ktor.client.engine.cio.CIO

actual fun httpEngine() = CIO.create()