package com.hayvanogreniyorum.model

enum class QuizTipi {
    ISIM_BUL,    // Emojiyi gör, ismi bul
    SES_BUL,     // Sesi gör, hayvanı bul
    EMOJI_BUL    // İsmi gör, emojiyi bul
}

data class QuizSorusu(
    val tip: QuizTipi,
    val dogruHayvan: Hayvan,
    val secenekler: List<Hayvan>,
    val soru: String,
    val gosterilen: String
)

data class QuizSonucu(
    val toplamSoru: Int,
    val dogruSayisi: Int,
    val puan: Int,
    val kategori: Kategori?
)
