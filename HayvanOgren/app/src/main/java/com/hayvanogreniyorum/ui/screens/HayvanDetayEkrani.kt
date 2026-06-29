package com.hayvanogreniyorum.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.hayvanogreniyorum.model.Hayvan
import com.hayvanogreniyorum.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HayvanDetayEkrani(
    hayvan: Hayvan,
    ogrenildi: Boolean,
    onGeriDon: () -> Unit,
    onOgren: () -> Unit
) {
    val hayvanRengi = Color(hayvan.renk)

    // Zıplama animasyonu
    val infiniteTransition = rememberInfiniteTransition(label = "detay")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.12f,
        animationSpec = infiniteRepeatable(
            animation = tween(700, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )
    val rotation by infiniteTransition.animateFloat(
        initialValue = -5f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "rotation"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "${hayvan.emoji} ${hayvan.isim}",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onGeriDon) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Geri")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryGreen,
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
                        colors = listOf(hayvanRengi.copy(alpha = 0.3f), Color(0xFFF8FFF8))
                    )
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Büyük emoji
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .background(hayvanRengi.copy(alpha = 0.2f), RoundedCornerShape(90.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = hayvan.emoji,
                    fontSize = 100.sp,
                    modifier = Modifier
                        .scale(scale)
                        .rotate(rotation)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // İsim
            Text(
                text = hayvan.isim,
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF1B5E20)
            )

            // Kategori rozeti
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = PrimaryGreen.copy(alpha = 0.15f),
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = "${hayvan.kategori.goruntu} ${hayvan.kategori.ad}",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                    color = PrimaryGreenDark,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Ses kartı
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "🔊", fontSize = 32.sp)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Sesi",
                            fontSize = 12.sp,
                            color = Color(0xFF888888)
                        )
                        Text(
                            text = hayvan.ses,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = SecondaryOrange
                        )
                    }
                }
            }

            // Açıklama kartı
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "📖 Hakkında",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryGreenDark
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = hayvan.aciklama,
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        color = Color(0xFF333333)
                    )
                }
            }

            // Eğlenceli bilgi kartı
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "💡 Eğlenceli Bilgi",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1565C0)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = hayvan.eglenceliBilgi,
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        color = Color(0xFF333333)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Öğrendim butonu
            Button(
                onClick = {
                    if (!ogrenildi) onOgren()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(58.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (ogrenildi) SuccessGreen else PrimaryGreen
                )
            ) {
                Text(
                    text = if (ogrenildi) "✅ Öğrendim!" else "🎓 Öğrendim!",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
