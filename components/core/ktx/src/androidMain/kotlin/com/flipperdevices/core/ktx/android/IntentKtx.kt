package com.flipperdevices.core.ktx.android

import android.content.Intent
import androidx.core.os.bundleOf

fun Intent.toFullString(): String {
    val sb = StringBuilder()

    sb.append(toString())

    val localExtras = extras ?: return sb.toString()

    sb.append(". Extras{")
    localExtras.keySet().map {
        @Suppress("DEPRECATION")
        it to localExtras.get(it)
    }.joinTo(buffer = sb, separator = ",") { "${it.first}=${it.second}" }
    sb.append('}')

    return sb.toString()
}

/**
 * Extras for settings
 */
const val EXTRA_FRAGMENT_ARG_KEY = ":settings:fragment_args_key"
const val EXTRA_SHOW_FRAGMENT_ARGUMENTS = ":settings:show_fragment_args"

/**
 * Undocumented feature to highlight an item in system settings.
 *
 * @param string package name or ComponentName string, can also be a key to the setting.
 */
fun Intent.highlightSettingsTo(string: String): Intent {
    putExtra(EXTRA_FRAGMENT_ARG_KEY, string)
    val bundle = bundleOf(EXTRA_FRAGMENT_ARG_KEY to string)
    putExtra(EXTRA_SHOW_FRAGMENT_ARGUMENTS, bundle)
    return this
}
