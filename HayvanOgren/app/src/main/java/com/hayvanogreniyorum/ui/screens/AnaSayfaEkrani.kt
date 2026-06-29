package com.hayvanogreniyorum.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.hayvanogreniyorum.model.Kategori
import com.hayvanogreniyorum.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnaSayfaEkrani(
    ogrenilmisHayvanSayisi: Int,
    tumHayvanSayisi: Int,
    onKategoriSec: (Kategori) -> Unit,
    onQuizBaslat: () -> Unit,
    onIstatistikGit: () -> Unit
) {
    val kategoriler = listOf(
        Triple(Kategori.CIFTLIK, CiftlikRenk, "🌾"),
        Triple(Kategori.ORMAN, OrmanRenk, "🌲"),
        Triple(Kategori.DENIZ, DenizRenk, "🌊"),
        Triple(Kategori.SAFARI, SafariRenk, "🌍"),
        Triple(Kategori.EVCIL, EvcilRenk, "🏠")
    )

    val ilerlemeYuzdesi = if (tumHayvanSayisi > 0) ogrenilmisHayvanSayisi.toFloat() / tumHayvanSayisi else 0f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1B5E20), Color(0xFF4CAF50), Color(0xFFF8FFF8))
                )
            )
            .verticalScroll(rememberScrollState())
    ) {
        // Üst başlık bölümü
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, bottom = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Animasyonlu emoji
                val infiniteTransition = rememberInfiniteTransition(label = "bounce")
                val scale by infiniteTransition.animateFloat(
                    initialValue = 1f,
                    targetValue = 1.15f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(800, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "scale"
                )

                Text(
                    text = "🐾",
                    fontSize = 64.sp,
                    modifier = Modifier.scale(scale)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Hayvanları",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
                Text(
                    text = "Öğreniyorum!",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFFFFD54F)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Eğlenerek öğren, keşfet!",
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.85f)
                )
            }
        }

        // İstatistik kartı
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "📊 İlerleme",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "$ogrenilmisHayvanSayisi / $tumHayvanSayisi hayvan",
                        color = PrimaryGreen,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                LinearProgressIndicator(
                    progress = ilerlemeYuzdesi,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    color = PrimaryGreen,
                    trackColor = Color(0xFFE8F5E9)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = when {
                        ilerlemeYuzdesi == 0f -> "Öğrenmeye başla! 🚀"
                        ilerlemeYuzdesi < 0.3f -> "Harika gidiyorsun! 🌱"
                        ilerlemeYuzdesi < 0.7f -> "Yarıya yaklaştın! ⭐"
                        ilerlemeYuzdesi < 1f -> "Neredeyse tamamladın! 🔥"
                        else -> "Hepsini öğrendin! 🏆🎉"
                    },
                    color = Color(0xFF555555),
                    fontSize = 13.sp
                )
            }
        }

        // Kategoriler başlığı
        Text(
            text = "🗂️ Kategoriler",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
        )

        // Kategori grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.height(320.dp)
        ) {
            items(kategoriler) { (kategori, renk, ikon) ->
                KategoriKarti(
                    kategori = kategori,
                    renk = renk,
                    ikon = ikon,
                    onClick = { onKategoriSec(kategori) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quiz butonu
        Button(
            onClick = onQuizBaslat,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(60.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = SecondaryOrange)
        ) {
            Text(text = "🎯  Quiz Oyna!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // İstatistik butonu
        OutlinedButton(
            onClick = onIstatistikGit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, PrimaryGreen)
        ) {
            Icon(Icons.Default.BarChart, contentDescription = null, tint = PrimaryGreen)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "İstatistiklerim", fontSize = 16.sp, color = PrimaryGreen, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun KategoriKarti(
    kategori: Kategori,
    renk: Color,
    ikon: String,
    onClick: () -> Unit
) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.95f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "card_scale"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale)
            .clickable {
                pressed = true
                onClick()
            },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = renk.copy(alpha = 0.25f)),
        border = BorderStroke(2.dp, renk),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = kategori.emoji, fontSize = 40.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = ikon, fontSize = 18.sp)
            Text(
                text = kategori.ad,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF1A1A1A)
            )
        }
    }

    LaunchedEffect(pressed) {
        if (pressed) {
            kotlinx.coroutines.delay(150)
            pressed = false
        }
    }
}
