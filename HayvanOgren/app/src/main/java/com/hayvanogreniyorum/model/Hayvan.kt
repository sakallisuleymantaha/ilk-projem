package com.hayvanogreniyorum.model

data class Hayvan(
    val id: String,
    val isim: String,
    val emoji: String,
    val kategori: Kategori,
    val ses: String,
    val aciklama: String,
    val eglenceliBilgi: String,
    val renk: Long
)

enum class Kategori(val goruntu: String, val ad: String, val emoji: String) {
    CIFTLIK("🌾", "Çiftlik", "🐄"),
    ORMAN("🌲", "Orman", "🦊"),
    DENIZ("🌊", "Deniz", "🐬"),
    SAFARI("🌍", "Safari", "🦁"),
    EVCIL("🏠", "Evcil", "🐶")
}
