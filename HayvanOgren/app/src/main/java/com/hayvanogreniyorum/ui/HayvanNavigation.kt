package com.hayvanogreniyorum.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hayvanogreniyorum.AnaViewModel
import com.hayvanogreniyorum.data.HayvanRepository
import com.hayvanogreniyorum.model.Kategori
import com.hayvanogreniyorum.ui.screens.*

@Composable
fun HayvanNavigation(viewModel: AnaViewModel) {
    val navController = rememberNavController()
    val uiDurum by viewModel.uiDurum.collectAsState()

    NavHost(navController = navController, startDestination = Ekran.AnaSayfa.rota) {

        composable(Ekran.AnaSayfa.rota) {
            AnaSayfaEkrani(
                ogrenilmisHayvanSayisi = uiDurum.ogrenilmisHayvanlar.size,
                tumHayvanSayisi = HayvanRepository.tumHayvanlar.size,
                onKategoriSec = { kategori ->
                    navController.navigate(Ekran.HayvanListesi.rotaOlustur(kategori.name))
                },
                onQuizBaslat = {
                    viewModel.quizBaslat(null)
                    navController.navigate(Ekran.Quiz.rotaOlustur("TUMU"))
                },
                onIstatistikGit = {
                    navController.navigate(Ekran.Istatistik.rota)
                }
            )
        }

        composable(
            route = Ekran.HayvanListesi.rota,
            arguments = listOf(navArgument("kategori") { type = NavType.StringType })
        ) { backStackEntry ->
            val kategoriAdi = backStackEntry.arguments?.getString("kategori") ?: ""
            val kategori = Kategori.values().find { it.name == kategoriAdi }
            HayvanListesiEkrani(
                kategori = kategori,
                hayvanlar = if (kategori != null) HayvanRepository.kategoriyeGoreGetir(kategori)
                            else HayvanRepository.tumHayvanlar,
                ogrenilmisler = uiDurum.ogrenilmisHayvanlar,
                onHayvanSec = { hayvan ->
                    navController.navigate(Ekran.HayvanDetay.rotaOlustur(hayvan.id))
                },
                onGeriDon = { navController.popBackStack() },
                onQuizBaslat = {
                    viewModel.quizBaslat(kategori)
                    navController.navigate(Ekran.Quiz.rotaOlustur(kategoriAdi))
                }
            )
        }

        composable(
            route = Ekran.HayvanDetay.rota,
            arguments = listOf(navArgument("hayvanId") { type = NavType.StringType })
        ) { backStackEntry ->
            val hayvanId = backStackEntry.arguments?.getString("hayvanId") ?: ""
            val hayvan = HayvanRepository.tumHayvanlar.find { it.id == hayvanId }
            hayvan?.let {
                HayvanDetayEkrani(
                    hayvan = it,
                    ogrenildi = uiDurum.ogrenilmisHayvanlar.contains(it.id),
                    onGeriDon = { navController.popBackStack() },
                    onOgren = { viewModel.hayvanGoruldu(it.id) }
                )
            }
        }

        composable(
            route = Ekran.Quiz.rota,
            arguments = listOf(navArgument("kategori") { type = NavType.StringType })
        ) {
            QuizEkrani(
                uiDurum = uiDurum,
                onCevapSec = { hayvan -> viewModel.cevapSec(hayvan) },
                onSonrakiSoru = {
                    if (uiDurum.quizBitti) {
                        val puan = uiDurum.dogru * 10
                        navController.navigate(
                            Ekran.QuizSonuc.rotaOlustur(puan, uiDurum.dogru, uiDurum.mevcutQuizSorulari.size)
                        ) {
                            popUpTo(Ekran.Quiz.rota) { inclusive = true }
                        }
                    } else {
                        viewModel.sonrakiSoru()
                    }
                },
                onCikis = { navController.popBackStack() }
            )
        }

        composable(
            route = Ekran.QuizSonuc.rota,
            arguments = listOf(
                navArgument("puan") { type = NavType.IntType },
                navArgument("dogru") { type = NavType.IntType },
                navArgument("toplam") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val puan = backStackEntry.arguments?.getInt("puan") ?: 0
            val dogru = backStackEntry.arguments?.getInt("dogru") ?: 0
            val toplam = backStackEntry.arguments?.getInt("toplam") ?: 0
            QuizSonucEkrani(
                puan = puan,
                dogru = dogru,
                toplam = toplam,
                onAnaSayfaGit = {
                    navController.navigate(Ekran.AnaSayfa.rota) {
                        popUpTo(Ekran.AnaSayfa.rota) { inclusive = true }
                    }
                },
                onTekrarOyna = {
                    viewModel.quizBaslat(null)
                    navController.navigate(Ekran.Quiz.rotaOlustur("TUMU")) {
                        popUpTo(Ekran.QuizSonuc.rota) { inclusive = true }
                    }
                }
            )
        }

        composable(Ekran.Istatistik.rota) {
            IstatistikEkrani(
                uiDurum = uiDurum,
                onGeriDon = { navController.popBackStack() },
                onSifirla = { viewModel.herseySifirla() }
            )
        }
    }
}
