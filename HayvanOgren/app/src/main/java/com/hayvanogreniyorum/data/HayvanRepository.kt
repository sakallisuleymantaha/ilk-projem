package com.hayvanogreniyorum.data

import com.hayvanogreniyorum.model.Hayvan
import com.hayvanogreniyorum.model.Kategori

object HayvanRepository {

    val tumHayvanlar: List<Hayvan> = listOf(
        // ÇIFTLIK
        Hayvan(
            id = "inek",
            isim = "İnek",
            emoji = "🐄",
            kategori = Kategori.CIFTLIK,
            ses = "Möö möö!",
            aciklama = "İnek, süt veren ve çiftliklerde yaşayan büyük bir hayvandır.",
            eglenceliBilgi = "İnekler günde 4 kez sağılabilir ve bir inek yılda yaklaşık 10.000 litre süt verebilir!",
            renk = 0xFFE8D5B7
        ),
        Hayvan(
            id = "koyun",
            isim = "Koyun",
            emoji = "🐑",
            kategori = Kategori.CIFTLIK,
            ses = "Meee meee!",
            aciklama = "Koyunlar yünlü tüyleriyle tanınan çiftlik hayvanlarıdır.",
            eglenceliBilgi = "Bir koyunun yünü yılda bir kez kırpılır ve bu yün kazak, battaniye yapımında kullanılır!",
            renk = 0xFFF5F5F0
        ),
        Hayvan(
            id = "tavuk",
            isim = "Tavuk",
            emoji = "🐔",
            kategori = Kategori.CIFTLIK,
            ses = "Gıdak gıdak!",
            aciklama = "Tavuklar yumurta yiyen ve çiftliklerde yaşayan kümes hayvanlarıdır.",
            eglenceliBilgi = "Bir tavuk yılda yaklaşık 300 yumurta yiyebilir. Tavuklar aslında uçabilir ama çok fazla değil!",
            renk = 0xFFFFD700
        ),
        Hayvan(
            id = "at",
            isim = "At",
            emoji = "🐎",
            kategori = Kategori.CIFTLIK,
            ses = "Hiii hiii!",
            aciklama = "Atlar hızlı koşan ve insanlarla çok eski zamanlardan beri dost olan hayvanlardır.",
            eglenceliBilgi = "Atlar ayakta uyuyabilir! Ayrıca bir at saatte 70 km hıza ulaşabilir.",
            renk = 0xFF8B6914
        ),
        Hayvan(
            id = "domuz",
            isim = "Domuz",
            emoji = "🐷",
            kategori = Kategori.CIFTLIK,
            ses = "Oink oink!",
            aciklama = "Domuzlar pembe renkleri ve yuvarlak burunlarıyla tanınan zeki çiftlik hayvanlarıdır.",
            eglenceliBilgi = "Domuzlar aslında çok zekidir! Köpeklerden bile daha zeki olduklarına dair araştırmalar var.",
            renk = 0xFFFFB6C1
        ),

        // ORMAN
        Hayvan(
            id = "tilki",
            isim = "Tilki",
            emoji = "🦊",
            kategori = Kategori.ORMAN,
            ses = "Vauu vauu!",
            aciklama = "Tilkiler kızıl tüylü, uzun kuyruklu ve çok zeki ormanlık hayvanlarıdır.",
            eglenceliBilgi = "Tilkiler 40'tan fazla farklı ses çıkarabilir! Ayrıca mükemmel hafızaları vardır.",
            renk = 0xFFFF6B35
        ),
        Hayvan(
            id = "ayı",
            isim = "Ayı",
            emoji = "🐻",
            kategori = Kategori.ORMAN,
            ses = "Grrrr!",
            aciklama = "Ayılar büyük ve güçlü ormanlık hayvanlardır. Kışın mağaralarda uyurlar.",
            eglenceliBilgi = "Ayılar kış uykusuna yatmadan önce vücutlarında yeterince yağ depolamak için çok yer.",
            renk = 0xFF8B4513
        ),
        Hayvan(
            id = "kurt",
            isim = "Kurt",
            emoji = "🐺",
            kategori = Kategori.ORMAN,
            ses = "Avuuuu!",
            aciklama = "Kurtlar sürüler halinde yaşayan, aya uluyabilen güçlü hayvanlardır.",
            eglenceliBilgi = "Kurtlar bir gecede 70 km yürüyebilir! Sürü halinde çok iyi takım çalışması yaparlar.",
            renk = 0xFF808080
        ),
        Hayvan(
            id = "geyik",
            isim = "Geyik",
            emoji = "🦌",
            kategori = Kategori.ORMAN,
            ses = "Bee bee!",
            aciklama = "Geyikler uzun bacaklı ve boynuzlu zarif ormanlık hayvanlardır.",
            eglenceliBilgi = "Erkek geyiklerin boynuzları her yıl düşer ve yeniden büyür! Bu boynuzlar çok hızlı büyür.",
            renk = 0xFFC19A6B
        ),
        Hayvan(
            id = "sincap",
            isim = "Sincap",
            emoji = "🐿️",
            kategori = Kategori.ORMAN,
            ses = "Çıt çıt!",
            aciklama = "Sincaplar uzun tüylü kuyrukları ve ağaçlarda ustalıkla zıplamaları ile tanınır.",
            eglenceliBilgi = "Sincaplar kış için fındık ve ceviz saklar ama çoğunu nereye sakladıklarını unuturlar!",
            renk = 0xFFD2691E
        ),

        // DENİZ
        Hayvan(
            id = "yunus",
            isim = "Yunus",
            emoji = "🐬",
            kategori = Kategori.DENIZ,
            ses = "Eee eee!",
            aciklama = "Yunuslar çok zeki ve sosyal deniz memelileridir. İnsanlarla oyun oynamayı severler.",
            eglenceliBilgi = "Yunuslar uyurken beynin yarısı uyanık kalır. Bu sayede boğulmadan uyuyabilirler!",
            renk = 0xFF4FA3C7
        ),
        Hayvan(
            id = "balik",
            isim = "Balık",
            emoji = "🐟",
            kategori = Kategori.DENIZ,
            ses = "Blub blub!",
            aciklama = "Balıklar suyun içinde yaşayan, solungaçları ile nefes alan hayvanlardır.",
            eglenceliBilgi = "Dünyanın okyanuslarında 30.000'den fazla farklı balık türü yaşamaktadır!",
            renk = 0xFF1E90FF
        ),
        Hayvan(
            id = "ahtapot",
            isim = "Ahtapot",
            emoji = "🐙",
            kategori = Kategori.DENIZ,
            ses = "Sssss!",
            aciklama = "Ahtapotlar sekiz kollu ve çok zeki deniz canlılarıdır.",
            eglenceliBilgi = "Ahtapotların üç kalbi vardır! Ayrıca renk kör olmalarına rağmen renk değiştirebilirler.",
            renk = 0xFFFF4500
        ),
        Hayvan(
            id = "penguen",
            isim = "Penguen",
            emoji = "🐧",
            kategori = Kategori.DENIZ,
            ses = "Arf arf!",
            aciklama = "Penguenler uçamayan ama yüzmede çok başarılı kuşlardır.",
            eglenceliBilgi = "Penguenler su altında saatte 35 km hıza ulaşabilir! Çiftlerini ömür boyu seçerler.",
            renk = 0xFF2F2F2F
        ),
        Hayvan(
            id = "kaplumbaga",
            isim = "Kaplumbağa",
            emoji = "🐢",
            kategori = Kategori.DENIZ,
            ses = "Hssss!",
            aciklama = "Kaplumbağalar sert kabukları olan ve çok uzun yaşayan hayvanlardır.",
            eglenceliBilgi = "Dev kaplumbağalar 150 yıldan fazla yaşayabilir! Dünyada yaşayan en eski hayvanlardan biridir.",
            renk = 0xFF228B22
        ),

        // SAFARİ
        Hayvan(
            id = "aslan",
            isim = "Aslan",
            emoji = "🦁",
            kategori = Kategori.SAFARI,
            ses = "Rawrrr!",
            aciklama = "Aslanlar güçlü yeleleriyle tanınan, savanada yaşayan büyük kedilerdir.",
            eglenceliBilgi = "Aslanların kükreyişi 8 km uzaklıktan duyulabilir! Dişi aslanlar avcılığı üstlenir.",
            renk = 0xFFDAA520
        ),
        Hayvan(
            id = "fil",
            isim = "Fil",
            emoji = "🐘",
            kategori = Kategori.SAFARI,
            ses = "Parrr!",
            aciklama = "Filler karada yaşayan en büyük hayvanlardır. Uzun hortumları ve büyük kulakları vardır.",
            eglenceliBilgi = "Filler 50 yıl boyunca kendilerini aynada tanıyabilir. Ayrıca ölü akrabalarını hatırlarlar.",
            renk = 0xFF808080
        ),
        Hayvan(
            id = "zürafa",
            isim = "Zürafa",
            emoji = "🦒",
            kategori = Kategori.SAFARI,
            ses = "Muhh!",
            aciklama = "Zürafalar yeryüzündeki en uzun boylu hayvanlardır. Uzun boyunlarıyla ağaçlardan yaprak yerler.",
            eglenceliBilgi = "Zürafa günde sadece 30 dakika uyur! Boynu çok uzun olduğu için içmesi çok zordur.",
            renk = 0xFFFFD700
        ),
        Hayvan(
            id = "zebra",
            isim = "Zebra",
            emoji = "🦓",
            kategori = Kategori.SAFARI,
            ses = "Hee hee!",
            aciklama = "Zebralar siyah-beyaz çizgileriyle tanınan Afrika'nın güzel hayvanlarıdır.",
            eglenceliBilgi = "Her zebraının çizgi deseni parmak izi gibi eşsizdir! Hiçbir iki zebra aynı desene sahip değildir.",
            renk = 0xFF2F2F2F
        ),
        Hayvan(
            id = "goril",
            isim = "Goril",
            emoji = "🦍",
            kategori = Kategori.SAFARI,
            ses = "Ugh ugh!",
            aciklama = "Goriller insanlarla akraba olan büyük primatlar olup ormanlarda yaşarlar.",
            eglenceliBilgi = "Goriller işaret dili öğrenebilir! Bazı goriller yüzlerce kelime öğrenmiştir.",
            renk = 0xFF2F2F2F
        ),
        Hayvan(
            id = "hipopotam",
            isim = "Hipopotam",
            emoji = "🦛",
            kategori = Kategori.SAFARI,
            ses = "Hnnn hnnn!",
            aciklama = "Hipopotamlar büyük ağızları ve kısa bacaklarıyla tanınan su hayvanlarıdır.",
            eglenceliBilgi = "Hipopotamlar su altında 5 dakika nefes tutabilir! Derilerinden kırmızı bir sıvı salgılarlar.",
            renk = 0xFFA0A0A0
        ),
        Hayvan(
            id = "leopar",
            isim = "Leopar",
            emoji = "🐆",
            kategori = Kategori.SAFARI,
            ses = "Grrrr!",
            aciklama = "Leoparlar benekli tüyleri ve ağaçlara tırmanabilme yetenekleriyle tanınır.",
            eglenceliBilgi = "Leoparlar avlarını ağaç dallarına taşıyabilir! Bu sayede diğer hayvanlardan korurlar.",
            renk = 0xFFDAA520
        ),

        // EVCİL
        Hayvan(
            id = "kopek",
            isim = "Köpek",
            emoji = "🐶",
            kategori = Kategori.EVCIL,
            ses = "Hav hav!",
            aciklama = "Köpekler insanlığın en sadık dostlarıdır. Binlerce yıldır insanlarla birlikte yaşarlar.",
            eglenceliBilgi = "Köpekler 100.000'den fazla farklı koku alabilir! Burundaki koku alma organı insanlardan 40 kat güçlüdür.",
            renk = 0xFFD2691E
        ),
        Hayvan(
            id = "kedi",
            isim = "Kedi",
            emoji = "🐱",
            kategori = Kategori.EVCIL,
            ses = "Miyav miyav!",
            aciklama = "Kediler bağımsız ve zarif evcil hayvanlardır. Mırlama sesleri insanları rahatlatır.",
            eglenceliBilgi = "Kediler günde 12-16 saat uyur! Yüksekten düşseler de her zaman dört ayak üzerine inerler.",
            renk = 0xFFFF8C00
        ),
        Hayvan(
            id = "tavşan",
            isim = "Tavşan",
            emoji = "🐰",
            kategori = Kategori.EVCIL,
            ses = "Çıt çıt!",
            aciklama = "Tavşanlar uzun kulakları ve tüylü kuyrukları olan sevimli evcil hayvanlardır.",
            eglenceliBilgi = "Tavşanlar mutlu olduklarında zıplayıp dönebilirler. Buna 'binky' denir!",
            renk = 0xFFFFC0CB
        ),
        Hayvan(
            id = "hamster",
            isim = "Hamster",
            emoji = "🐹",
            kategori = Kategori.EVCIL,
            ses = "Çıt çıt!",
            aciklama = "Hamsterlar şişirebilen yanakları ve gece aktif olmasıyla bilinen küçük evcil hayvanlardır.",
            eglenceliBilgi = "Hamsterlar yanakları içinde vücut ağırlıklarının %20'si kadar yiyecek taşıyabilir!",
            renk = 0xFFFFD700
        ),
        Hayvan(
            id = "papagan",
            isim = "Papağan",
            emoji = "🦜",
            kategori = Kategori.EVCIL,
            ses = "Merhaba!",
            aciklama = "Papağanlar konuşabilme yetenekleriyle tanınan renkli ve zeki kuşlardır.",
            eglenceliBilgi = "Bazı papağanlar 1000'den fazla kelime öğrenebilir! 80 yıla kadar yaşayabilirler.",
            renk = 0xFF00C853
        )
    )

    fun kategoriyeGoreGetir(kategori: com.hayvanogreniyorum.model.Kategori): List<Hayvan> {
        return tumHayvanlar.filter { it.kategori == kategori }
    }
}
