package com.hayvanogreniyorum.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.hayvanogreniyorum.UygulamaUiDurum
import com.hayvanogreniyorum.data.HayvanRepository
import com.hayvanogreniyorum.model.Kategori
import com.hayvanogreniyorum.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IstatistikEkrani(
    uiDurum: UygulamaUiDurum,
    onGeriDon: () -> Unit,
    onSifirla: () -> Unit
) {
    var sifirlaDialogGoster by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("📊 İstatistiklerim", fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = onGeriDon) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Geri")
                    }
                },
                actions = {
                    IconButton(onClick = { sifirlaDialogGoster = true }) {
                        Icon(Icons.Default.RestartAlt, contentDescription = "Sıfırla", tint = ErrorRed)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryGreen,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF8FFF8))
                .verticalScroll(rememberScrollState())
        ) {
            // Genel istatistikler
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "🏆 Genel Durum",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryGreenDark
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        IstatistikKarti(
                            emoji = "📚",
                            deger = "${uiDurum.ogrenilmisHayvanlar.size}",
                            etiket = "Öğrenilen",
                            renk = PrimaryGreen
                        )
                        IstatistikKarti(
                            emoji = "⭐",
                            deger = "${uiDurum.enYuksekPuan}",
                            etiket = "En Yüksek Puan",
                            renk = SecondaryOrange
                        )
                        IstatistikKarti(
                            emoji = "🎯",
                            deger = "${uiDurum.toplamQuiz}",
                            etiket = "Quiz Sorusu",
                            renk = TertiaryBlue
                        )
                    }

                    if (uiDurum.toplamQuiz > 0) {
                        Spacer(modifier = Modifier.height(16.dp))
                        val basariOrani = (uiDurum.toplamDogru.toFloat() / uiDurum.toplamQuiz * 100).toInt()
                        Text(
                            text = "Genel Başarı Oranı: %$basariOrani",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF555555)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        LinearProgressIndicator(
                            progress = uiDurum.toplamDogru.toFloat() / uiDurum.toplamQuiz,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp),
                            color = PrimaryGreen,
                            trackColor = Color(0xFFE8F5E9)
                        )
                    }
                }
            }

            // Kategori bazlı ilerleme
            Text(
                text = "📂 Kategori İlerleme",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryGreenDark,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            val kategoriRenkleri = mapOf(
                Kategori.CIFTLIK to CiftlikRenk,
                Kategori.ORMAN to OrmanRenk,
                Kategori.DENIZ to DenizRenk,
                Kategori.SAFARI to SafariRenk,
                Kategori.EVCIL to EvcilRenk
            )

            Kategori.values().forEach { kategori ->
                val kategoridekiHayvanlar = HayvanRepository.kategoriyeGoreGetir(kategori)
                val ogrenilen = kategoridekiHayvanlar.count { uiDurum.ogrenilmisHayvanlar.contains(it.id) }
                val toplam = kategoridekiHayvanlar.size
                val renk = kategoriRenkleri[kategori] ?: PrimaryGreen

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(3.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = kategori.emoji, fontSize = 28.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = kategori.ad,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                            Text(
                                text = "$ogrenilen / $toplam",
                                fontWeight = FontWeight.SemiBold,
                                color = if (ogrenilen == toplam) PrimaryGreen else Color(0xFF666666)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        LinearProgressIndicator(
                            progress = if (toplam > 0) ogrenilen.toFloat() / toplam else 0f,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp),
                            color = renk,
                            trackColor = renk.copy(alpha = 0.2f)
                        )
                        if (ogrenilen == toplam) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "✅ Tamamlandı!",
                                color = PrimaryGreen,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Rozetler bölümü
            Text(
                text = "🎖️ Rozetler",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryGreenDark,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                listOf(
                    Triple("🌱", "İlk Hayvan", uiDurum.ogrenilmisHayvanlar.isNotEmpty()),
                    Triple("⭐", "Quiz Ustası", uiDurum.enYuksekPuan >= 80),
                    Triple("🏆", "Her Şeyi Öğren", uiDurum.ogrenilmisHayvanlar.size >= HayvanRepository.tumHayvanlar.size)
                ).forEach { (emoji, ad, kazanildi) ->
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (kazanildi) Color(0xFFFFF9C4) else Color(0xFFF5F5F5)
                        ),
                        border = if (kazanildi) androidx.compose.foundation.BorderStroke(2.dp, SecondaryOrange) else null
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = if (kazanildi) emoji else "🔒",
                                fontSize = 32.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = ad,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                color = if (kazanildi) Color(0xFF555555) else Color(0xFFAAAAAA)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }

    if (sifirlaDialogGoster) {
        AlertDialog(
            onDismissRequest = { sifirlaDialogGoster = false },
            title = { Text("⚠️ Her şeyi sıfırla?") },
            text = { Text("Tüm ilerleme, puanlar ve öğrenilen hayvanlar silinecek. Bu işlem geri alınamaz!") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onSifirla()
                        sifirlaDialogGoster = false
                    }
                ) {
                    Text("Sıfırla", color = ErrorRed, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { sifirlaDialogGoster = false }) {
                    Text("İptal")
                }
            }
        )
    }
}

@Composable
fun IstatistikKarti(emoji: String, deger: String, etiket: String, renk: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(renk.copy(alpha = 0.15f), RoundedCornerShape(32.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = emoji, fontSize = 28.sp)
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = deger, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = renk)
        Text(text = etiket, fontSize = 11.sp, color = Color(0xFF888888), textAlign = TextAlign.Center)
    }
}
