package com.hayvanogreniyorum.ui

sealed class Ekran(val rota: String) {
    object AnaSayfa : Ekran("ana_sayfa")
    object HayvanListesi : Ekran("hayvan_listesi/{kategori}") {
        fun rotaOlustur(kategori: String) = "hayvan_listesi/$kategori"
    }
    object HayvanDetay : Ekran("hayvan_detay/{hayvanId}") {
        fun rotaOlustur(hayvanId: String) = "hayvan_detay/$hayvanId"
    }
    object Quiz : Ekran("quiz/{kategori}") {
        fun rotaOlustur(kategori: String) = "quiz/$kategori"
    }
    object QuizSonuc : Ekran("quiz_sonuc/{puan}/{dogru}/{toplam}") {
        fun rotaOlustur(puan: Int, dogru: Int, toplam: Int) = "quiz_sonuc/$puan/$dogru/$toplam"
    }
    object Istatistik : Ekran("istatistik")
}
