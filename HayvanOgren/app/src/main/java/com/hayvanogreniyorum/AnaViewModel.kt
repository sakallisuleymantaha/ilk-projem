package com.hayvanogreniyorum

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hayvanogreniyorum.data.HayvanRepository
import com.hayvanogreniyorum.data.IlerlemeDataStore
import com.hayvanogreniyorum.data.SesYoneticisi
import com.hayvanogreniyorum.model.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class UygulamaUiDurum(
    val ogrenilmisHayvanlar: Set<String> = emptySet(),
    val enYuksekPuan: Int = 0,
    val toplamQuiz: Int = 0,
    val toplamDogru: Int = 0,
    val mevcutQuizSorulari: List<QuizSorusu> = emptyList(),
    val mevcutSoruIndex: Int = 0,
    val dogru: Int = 0,
    val quizBitti: Boolean = false,
    val secilenCevap: String? = null,
    val dogruMu: Boolean? = null
)

class AnaViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = IlerlemeDataStore(application)
    val sesYoneticisi = SesYoneticisi(application)

    private val _uiDurum = MutableStateFlow(UygulamaUiDurum())
    val uiDurum: StateFlow<UygulamaUiDurum> = _uiDurum.asStateFlow()

    val tumHayvanlar = HayvanRepository.tumHayvanlar

    init {
        viewModelScope.launch {
            combine(
                dataStore.ogrenilmisHayvanlar,
                dataStore.enYuksekPuan,
                dataStore.toplamQuiz,
                dataStore.toplamDogru
            ) { ogrenilen, puan, quiz, dogru ->
                _uiDurum.update {
                    it.copy(
                        ogrenilmisHayvanlar = ogrenilen,
                        enYuksekPuan = puan,
                        toplamQuiz = quiz,
                        toplamDogru = dogru
                    )
                }
            }.collect()
        }
    }

    fun hayvanGoruldu(hayvanId: String) {
        viewModelScope.launch {
            dataStore.hayvanOgrendi(hayvanId)
        }
        sesYoneticisi.tiklaTitret()
    }

    fun quizBaslat(kategori: Kategori?) {
        val havuz = if (kategori != null) {
            HayvanRepository.kategoriyeGoreGetir(kategori)
        } else {
            HayvanRepository.tumHayvanlar
        }

        val karistir = havuz.shuffled().take(10)
        val sorular = karistir.map { dogruHayvan ->
            val yanlis = havuz.filter { it.id != dogruHayvan.id }.shuffled().take(3)
            val secenekler = (yanlis + dogruHayvan).shuffled()
            val tip = QuizTipi.values().random()
            val (soru, gosterilen) = when (tip) {
                QuizTipi.ISIM_BUL -> Pair("Bu hangi hayvan?", dogruHayvan.emoji)
                QuizTipi.SES_BUL -> Pair("Bu sesi hangi hayvan çıkarır?", dogruHayvan.ses)
                QuizTipi.EMOJI_BUL -> Pair("${dogruHayvan.isim} hangisi?", dogruHayvan.isim)
            }
            QuizSorusu(
                tip = tip,
                dogruHayvan = dogruHayvan,
                secenekler = secenekler,
                soru = soru,
                gosterilen = gosterilen
            )
        }

        _uiDurum.update {
            it.copy(
                mevcutQuizSorulari = sorular,
                mevcutSoruIndex = 0,
                dogru = 0,
                quizBitti = false,
                secilenCevap = null,
                dogruMu = null
            )
        }
    }

    fun cevapSec(secilen: Hayvan) {
        val mevcutDurum = _uiDurum.value
        val mevcutSoru = mevcutDurum.mevcutQuizSorulari.getOrNull(mevcutDurum.mevcutSoruIndex) ?: return
        val dogruMu = secilen.id == mevcutSoru.dogruHayvan.id

        if (dogruMu) sesYoneticisi.dogruCevapTitret()
        else sesYoneticisi.yanlisCevapTitret()

        _uiDurum.update {
            it.copy(
                secilenCevap = secilen.id,
                dogruMu = dogruMu,
                dogru = if (dogruMu) it.dogru + 1 else it.dogru
            )
        }
    }

    fun sonrakiSoru() {
        val mevcutDurum = _uiDurum.value
        val sonrakiIndex = mevcutDurum.mevcutSoruIndex + 1

        if (sonrakiIndex >= mevcutDurum.mevcutQuizSorulari.size) {
            val puan = mevcutDurum.dogru * 10
            viewModelScope.launch {
                dataStore.quizSonucuKaydet(
                    puan,
                    mevcutDurum.dogru,
                    mevcutDurum.mevcutQuizSorulari.size
                )
            }
            _uiDurum.update { it.copy(quizBitti = true) }
        } else {
            _uiDurum.update {
                it.copy(
                    mevcutSoruIndex = sonrakiIndex,
                    secilenCevap = null,
                    dogruMu = null
                )
            }
        }
    }

    fun herseySifirla() {
        viewModelScope.launch {
            dataStore.herseySifirla()
        }
    }
}
