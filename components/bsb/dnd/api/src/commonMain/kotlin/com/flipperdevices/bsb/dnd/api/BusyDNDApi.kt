package com.flipperdevices.bsb.dnd.api

interface BusyDNDApi {
    fun isDNDSupportActive(): Boolean
    fun enableSupport(): Result<Unit>
    fun disableSupport()
}
