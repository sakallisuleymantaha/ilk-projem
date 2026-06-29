package com.hayvanogreniyorum.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.hayvanogreniyorum.UygulamaUiDurum
import com.hayvanogreniyorum.model.Hayvan
import com.hayvanogreniyorum.model.QuizTipi
import com.hayvanogreniyorum.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizEkrani(
    uiDurum: UygulamaUiDurum,
    onCevapSec: (Hayvan) -> Unit,
    onSonrakiSoru: () -> Unit,
    onCikis: () -> Unit
) {
    val sorular = uiDurum.mevcutQuizSorulari
    if (sorular.isEmpty()) return

    val mevcutSoru = sorular.getOrNull(uiDurum.mevcutSoruIndex) ?: return
    val toplamSoru = sorular.size
    val ilerleme = (uiDurum.mevcutSoruIndex + 1).toFloat() / toplamSoru

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "🎯 Soru ${uiDurum.mevcutSoruIndex + 1} / $toplamSoru",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onCikis) {
                        Icon(Icons.Default.Close, contentDescription = "Çıkış")
                    }
                },
                actions = {
                    Text(
                        text = "⭐ ${uiDurum.dogru * 10}",
                        fontWeight = FontWeight.Bold,
                        color = SecondaryOrange,
                        modifier = Modifier.padding(end = 16.dp),
                        fontSize = 18.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1565C0),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF1565C0), Color(0xFF42A5F5), Color(0xFFF8FFF8))
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // İlerleme çubuğu
            LinearProgressIndicator(
                progress = ilerleme,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp),
                color = SecondaryOrange,
                trackColor = Color.White.copy(alpha = 0.3f)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Soru metni
            Text(
                text = mevcutSoru.soru,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Gösterilen (emoji, ses veya isim)
            val infiniteTransition = rememberInfiniteTransition(label = "quiz_bounce")
            val scale by infiniteTransition.animateFloat(
                initialValue = 1f,
                targetValue = 1.08f,
                animationSpec = infiniteRepeatable(
                    animation = tween(600),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "scale"
            )

            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(75.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = mevcutSoru.gosterilen,
                    fontSize = when (mevcutSoru.tip) {
                        QuizTipi.ISIM_BUL -> 80.sp
                        QuizTipi.SES_BUL -> 22.sp
                        QuizTipi.EMOJI_BUL -> 26.sp
                    },
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .scale(if (mevcutSoru.tip == QuizTipi.ISIM_BUL) scale else 1f)
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Cevap seçenekleri
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                mevcutSoru.secenekler.chunked(2).forEach { satir ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        satir.forEach { hayvan ->
                            CevapButonu(
                                hayvan = hayvan,
                                quizTipi = mevcutSoru.tip,
                                secilenCevap = uiDurum.secilenCevap,
                                dogruCevapId = mevcutSoru.dogruHayvan.id,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    if (uiDurum.secilenCevap == null) onCevapSec(hayvan)
                                }
                            )
                        }
                        if (satir.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Geri bildirim ve devam butonu
            AnimatedVisibility(
                visible = uiDurum.secilenCevap != null,
                enter = slideInVertically() + fadeIn()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = if (uiDurum.dogruMu == true) "🎉 Harika! Doğru cevap!" else "😅 Yanlış! Doğru cevap: ${mevcutSoru.dogruHayvan.isim} ${mevcutSoru.dogruHayvan.emoji}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (uiDurum.dogruMu == true) Color(0xFF4CAF50) else Color(0xFFFF5722),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = onSonrakiSoru,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = SecondaryOrange)
                    ) {
                        Text(
                            text = if (uiDurum.mevcutSoruIndex + 1 >= toplamSoru) "🏆 Sonuçları Gör!" else "➡️ Sonraki Soru",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CevapButonu(
    hayvan: Hayvan,
    quizTipi: QuizTipi,
    secilenCevap: String?,
    dogruCevapId: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val secilenMi = secilenCevap == hayvan.id
    val dogruMu = hayvan.id == dogruCevapId
    val gosterilsinMi = secilenCevap != null

    val renk = when {
        gosterilsinMi && dogruMu -> Color(0xFF4CAF50)
        gosterilsinMi && secilenMi && !dogruMu -> Color(0xFFF44336)
        else -> Color.White
    }

    val metin = when (quizTipi) {
        QuizTipi.ISIM_BUL -> hayvan.isim
        QuizTipi.SES_BUL -> "${hayvan.emoji}\n${hayvan.isim}"
        QuizTipi.EMOJI_BUL -> hayvan.emoji
    }

    Card(
        modifier = modifier
            .height(80.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = renk),
        border = BorderStroke(2.dp, if (secilenMi) renk.copy(alpha = 0f) else Color.White.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(if (secilenMi) 8.dp else 4.dp),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = metin,
                fontSize = if (quizTipi == QuizTipi.EMOJI_BUL) 36.sp else 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = if (gosterilsinMi && (dogruMu || secilenMi)) Color.White else Color(0xFF1A1A1A),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
