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
        Int::class,
        Long::class,
        String::class,
        Float::class,
        Double::class -> throw NotImplementedError()
        Boolean::class -> getBoolean(key, default as Boolean) as T
        else -> getSerializable(serializer<T>(), key, default)
    }

inline fun <reified T : Any> PreferenceApi.getFlow(key: SettingsEnum, default: T): Flow<T> =
    when (T::class) {
        Int::class,
        Long::class,
        String::class,
        Float::class,
        Double::class -> throw NotImplementedError()
        Boolean::class -> getFlowBoolean(key, default as Boolean).map { it as T }
        else -> getFlowSerializable(serializer<T>(), key, default)
    }

inline fun <reified T : Any> PreferenceApi.set(key: SettingsEnum, value: T) =
    when (T::class) {
        Int::class,
        Long::class,
        String::class,
        Float::class,
        Double::class -> throw NotImplementedError()
        Boolean::class -> setBoolean(key, value as Boolean)
        else -> setSerializable(serializer<T>(), key, value)
    }