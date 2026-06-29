package com.hayvanogreniyorum.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.hayvanogreniyorum.model.Hayvan
import com.hayvanogreniyorum.model.Kategori
import com.hayvanogreniyorum.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HayvanListesiEkrani(
    kategori: Kategori?,
    hayvanlar: List<Hayvan>,
    ogrenilmisler: Set<String>,
    onHayvanSec: (Hayvan) -> Unit,
    onGeriDon: () -> Unit,
    onQuizBaslat: () -> Unit
) {
    val baslik = kategori?.ad ?: "Tüm Hayvanlar"
    val kategoriEmoji = kategori?.emoji ?: "🐾"

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = kategoriEmoji, fontSize = 24.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = baslik,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onGeriDon) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Geri")
                    }
                },
                actions = {
                    IconButton(onClick = onQuizBaslat) {
                        Icon(Icons.Default.Quiz, contentDescription = "Quiz", tint = SecondaryOrange)
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
        ) {
            // İlerleme çubuğu
            val ogrenilmis = hayvanlar.count { ogrenilmisler.contains(it.id) }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "✅ $ogrenilmis / ${hayvanlar.size} hayvan öğrenildi",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        LinearProgressIndicator(
                            progress = if (hayvanlar.isNotEmpty()) ogrenilmis.toFloat() / hayvanlar.size else 0f,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp),
                            color = PrimaryGreen,
                            trackColor = Color(0xFFE8F5E9)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = onQuizBaslat,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = SecondaryOrange),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(text = "🎯 Quiz", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(hayvanlar) { hayvan ->
                    HayvanKarti(
                        hayvan = hayvan,
                        ogrenildi = ogrenilmisler.contains(hayvan.id),
                        onClick = { onHayvanSec(hayvan) }
                    )
                }
                item(span = { GridItemSpan(3) }) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun HayvanKarti(
    hayvan: Hayvan,
    ogrenildi: Boolean,
    onClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "jump")
    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -6f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "offsetY"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (ogrenildi) Color(0xFFE8F5E9) else Color.White
        ),
        border = if (ogrenildi) BorderStroke(2.dp, PrimaryGreen) else null,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = hayvan.emoji,
                    fontSize = 40.sp,
                    modifier = Modifier.offset(y = offsetY.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = hayvan.isim,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
            if (ogrenildi) {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(18.dp)
                        .background(PrimaryGreen, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "✓", fontSize = 10.sp, color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
