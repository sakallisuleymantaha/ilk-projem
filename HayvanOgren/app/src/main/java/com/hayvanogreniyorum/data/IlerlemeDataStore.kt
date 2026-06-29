package com.hayvanogreniyorum.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "hayvan_ilerleme")

class IlerlemeDataStore(private val context: Context) {

    companion object {
        val OGRENILENLER_KEY = stringSetPreferencesKey("ogrenilen_hayvanlar")
        val EN_YUKSEK_PUAN_KEY = intPreferencesKey("en_yuksek_puan")
        val TOPLAM_QUIZ_KEY = intPreferencesKey("toplam_quiz")
        val TOPLAM_DOGRU_KEY = intPreferencesKey("toplam_dogru")
    }

    val ogrenilmisHayvanlar: Flow<Set<String>> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }
        .map { preferences ->
            preferences[OGRENILENLER_KEY] ?: emptySet()
        }

    val enYuksekPuan: Flow<Int> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }
        .map { preferences ->
            preferences[EN_YUKSEK_PUAN_KEY] ?: 0
        }

    val toplamQuiz: Flow<Int> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }
        .map { preferences ->
            preferences[TOPLAM_QUIZ_KEY] ?: 0
        }

    val toplamDogru: Flow<Int> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }
        .map { preferences ->
            preferences[TOPLAM_DOGRU_KEY] ?: 0
        }

    suspend fun hayvanOgrendi(hayvanId: String) {
        context.dataStore.edit { preferences ->
            val mevcutSet = preferences[OGRENILENLER_KEY] ?: emptySet()
            preferences[OGRENILENLER_KEY] = mevcutSet + hayvanId
        }
    }

    suspend fun quizSonucuKaydet(puan: Int, dogru: Int, toplam: Int) {
        context.dataStore.edit { preferences ->
            val mevcutPuan = preferences[EN_YUKSEK_PUAN_KEY] ?: 0
            if (puan > mevcutPuan) {
                preferences[EN_YUKSEK_PUAN_KEY] = puan
            }
            val mevcutQuiz = preferences[TOPLAM_QUIZ_KEY] ?: 0
            preferences[TOPLAM_QUIZ_KEY] = mevcutQuiz + toplam
            val mevcutDogru = preferences[TOPLAM_DOGRU_KEY] ?: 0
            preferences[TOPLAM_DOGRU_KEY] = mevcutDogru + dogru
        }
    }

    suspend fun herseySifirla() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
