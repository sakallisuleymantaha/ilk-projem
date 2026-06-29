package com.hayvanogreniyorum.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.hayvanogreniyorum.ui.theme.*

@Composable
fun QuizSonucEkrani(
    puan: Int,
    dogru: Int,
    toplam: Int,
    onAnaSayfaGit: () -> Unit,
    onTekrarOyna: () -> Unit
) {
    val basariYuzdesi = if (toplam > 0) dogru.toFloat() / toplam else 0f

    val rozet = when {
        basariYuzdesi == 1f -> Triple("🏆", "Mükemmel!", Color(0xFFFFD700))
        basariYuzdesi >= 0.7f -> Triple("⭐", "Harika!", Color(0xFF4CAF50))
        basariYuzdesi >= 0.5f -> Triple("👍", "İyi!", Color(0xFF2196F3))
        else -> Triple("💪", "Daha İyi Olacak!", Color(0xFFFF9800))
    }

    val infiniteTransition = rememberInfiniteTransition(label = "trophy")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1B5E20), Color(0xFF4CAF50), Color(0xFFE8F5E9))
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Rozet emoji
        Text(
            text = rozet.first,
            fontSize = 100.sp,
            modifier = Modifier.scale(scale)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = rozet.second,
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        )

        Text(
            text = "Quiz Tamamlandı!",
            fontSize = 22.sp,
            color = Color.White.copy(alpha = 0.85f)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Puan kartı
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$puan",
                    fontSize = 72.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = rozet.third
                )
                Text(
                    text = "puan",
                    fontSize = 18.sp,
                    color = Color(0xFF888888)
                )

                Divider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = Color(0xFFEEEEEE)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    SonucItem(emoji = "✅", deger = "$dogru", etiket = "Doğru")
                    SonucItem(emoji = "❌", deger = "${toplam - dogru}", etiket = "Yanlış")
                    SonucItem(emoji = "📊", deger = "${(basariYuzdesi * 100).toInt()}%", etiket = "Başarı")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // İlerleme çubuğu
                LinearProgressIndicator(
                    progress = basariYuzdesi,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp),
                    color = rozet.third,
                    trackColor = Color(0xFFEEEEEE)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Butonlar
        Button(
            onClick = onTekrarOyna,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = SecondaryOrange)
        ) {
            Text(text = "🔄 Tekrar Oyna", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onAnaSayfaGit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
        ) {
            Text(text = "🏠 Ana Sayfa", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun SonucItem(emoji: String, deger: String, etiket: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = emoji, fontSize = 24.sp)
        Text(text = deger, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = etiket, fontSize = 12.sp, color = Color(0xFF888888))
    }
}
