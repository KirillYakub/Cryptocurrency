package com.example.cryptocurrency.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.cryptocurrency.common.Constants.CRYPTO_DATASTORE_PREFERENCES
import com.example.cryptocurrency.common.Constants.PREFERENCES_COMPLETED_KEY
import com.example.cryptocurrency.common.Constants.PREFERENCES_INPUT_WITHOUT_SIGN_IN_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = CRYPTO_DATASTORE_PREFERENCES
)

class DataStoreOperations(private val context: Context) {

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = PREFERENCES_COMPLETED_KEY)
        val onInputKey = booleanPreferencesKey(name = PREFERENCES_INPUT_WITHOUT_SIGN_IN_KEY)
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return context.dataStore.data
            .catch { _ ->
                emit(emptyPreferences())
            }
            .map { preferences ->
                preferences[PreferencesKey.onBoardingKey] ?: false
            }
    }

    suspend fun saveInputState(input: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKey.onInputKey] = input
        }
    }

    fun readInputState(): Flow<Boolean> {
        return context.dataStore.data
            .catch { _ ->
                emit(emptyPreferences())
            }
            .map { preferences ->
                preferences[PreferencesKey.onInputKey] ?: false
            }
    }
}