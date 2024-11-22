package com.flipperdevices.bsb.preference.api

import com.flipperdevices.bsb.preference.model.SettingsEnum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.serializer

interface PreferenceApi {
    fun getFlowBoolean(key: SettingsEnum, default: Boolean): Flow<Boolean>
    fun <T> getFlowSerializable(
        serializer: DeserializationStrategy<T>,
        key: SettingsEnum,
        default: T
    ): Flow<T>


    fun getBoolean(key: SettingsEnum, default: Boolean?): Boolean?
    fun <T> getSerializable(
        serializer: DeserializationStrategy<T>,
        key: SettingsEnum,
        default: T
    ): T

    fun setBoolean(key: SettingsEnum, value: Boolean)
    fun <T> setSerializable(
        serializer: SerializationStrategy<T>,
        key: SettingsEnum,
        value: T
    )
}

inline fun <reified T : Any> PreferenceApi.get(key: SettingsEnum, default: T): T =
    when (T::class) {
        //Int::class -> getIntOrNull(key) as T?
        //Long::class -> getLongOrNull(key) as T?
        //String::class -> getStringOrNull(key) as T?
        //Float::class -> getFloatOrNull(key) as T?
        //Double::class -> getDoubleOrNull(key) as T?
        Boolean::class -> getBoolean(key, default as Boolean) as T
        else -> getSerializable(serializer<T>(), key, default)
    }

inline fun <reified T : Any> PreferenceApi.getFlow(key: SettingsEnum, default: T): Flow<T> =
    when (T::class) {
        //Int::class -> getIntOrNull(key) as T?
        //Long::class -> getLongOrNull(key) as T?
        //String::class -> getStringOrNull(key) as T?
        //Float::class -> getFloatOrNull(key) as T?
        //Double::class -> getDoubleOrNull(key) as T?
        Boolean::class -> getFlowBoolean(key, default as Boolean).map { it as T }
        else -> getFlowSerializable(serializer<T>(), key, default)
    }

inline fun <reified T : Any> PreferenceApi.set(key: SettingsEnum, value: T) =
    when (T::class) {
        //Int::class -> putInt(key, value as Int)
        //Long::class -> putLong(key, value as Long)
        //String::class -> putString(key, value as String)
        //Float::class -> putFloat(key, value as Float)
        //Double::class -> putDouble(key, value as Double)
        Boolean::class -> setBoolean(key, value as Boolean)
        else -> setSerializable(serializer<T>(), key, value)
    }