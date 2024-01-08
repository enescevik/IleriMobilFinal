# IleriMobilOdev2

## Haliç Üniversitesi Lisansüstü Eğitim Enstitüsü

**Bilgisayar Mühendisliği**

`BMY549 - İleri Mobil Programlama 1. Dönem Final Ödevi`

| Ödev Verilme Tarihi | - |
| ------ | ------ |
| **Ödev Teslim Tarihi** | **Sınav takviminde Haliç Üniversitesi Resmi Sayfasında ilan edilecektir.** |

# ÖDEV İÇERİĞİ
- MVVM mimarisi ile ve ViewBinding kullanarak bir android **uygulaması geliştirilerek** ve **yüzyüze olarak sunulacaktır.**
- Proje 3 ekrandan oluşacaktır ve aşağıda linki bulunan api ile iletişim kurularak disney filmleri listelenecektir.
https://gist.githubusercontent.com/skydoves/aa3bbbf495b0fa91db8a9e89f34e4873/raw/a1a13d37027e8920412da5f00f6a89c5a3dbfb9a/DisneyPosters.json
    - **E1 - Splash Ekranı:**
        - CounterDownTimer, Thread gibi yöntemlerle 3 saniye bu ekran gösterilip, 3 saniye sonunda E2 açılıp bu ekran ölecektir.
        - Tasarım size bırakılmıştır.
    - **E2 - Disney Filmleri Listesi:**
        - Bu ekranın çalışma şekli şöyle olacaktır; internet var ise internetten veriler çekilecek, eğer internet yok ise lokalde saklanan veriler basılacaktır. İnternet var ise ve lokalde verilerin bir kopyası saklanmadı ise veriler lokale de eklenecektir. Eğer hem internet yok hem de daha önceden lokale bir veri saklanmadı ise liste yerine ürünlerin yüklenmediğinde dair bir tasarım (Empty View) basılacaktır.
        - Burada ilgili karakterler **RecycleView** içerisine **CardView** olacak şekilde listelenecektir.
        - İstekler retorfit2 ile atılıp asenkronizasyon için Coroutine kullanılacaktır.
        - CardView içerisinde; id, poster resmi, karakter adı, playTime verileri olmalıdır.
        - Üst tarafında bir **searchview** olacak ve metin değiştikçe listeyi filtreleyecektir. Büyük küçük harfe duyarlı olacaktır.
        - Bu ekranda **geri tuşuna basılınca** kullanıcıya çıkmak isteyip istemediği sorulacak ve çıkışa basılırsa uygulama kapanacaktır. İptale basıldığında ise dialog kapatılacaktır.
        - Liste yüklenene kadar ve ProgressDialog ile lütfen bekleyiniz ibaresi gösterilecektir.
        - Bu ekranda üst tarafta bir sıralama butonu ve liste türü değiştirme butonu olacaktır.
        - Liste türü değiştir butonu tek buton olacaktır ve basıldıkça listeleme türü değişecektir.
        - Sıralama butonuna basıldığında ise **alert dialog ile liste açılacak,** listede isme göre artan ya da isme göre azalan sıra ile ve id ye göre artan azalan sıra ile listeleme yapılacaktır.
        - İlgili iteme tıklanınca detay verisi de eklenerek E3 ekranı açılacaktır.
    - **E3 - Detay Ekranı:**
        - İlgili item bu ekrana taşındıktan sonra Apideki diğer detayların tümü bu ekranda gösterilecek ve **nested scrool** olacaktır.

## Ödev Gönderimi
- Ödevler mail yoluyla teslim edilecektir. (Mail adresi: elifaltintaskahriman@halic.edu.tr)
- Sunum sırasında CD ile teslim edilecektir.
- Zip dosyası içerisinde projeden başka birşey olmamalıdır.
- **Proje Dosyası:** Numara_ad_soyad_kotlin_final_proje.**zip**
- **Rapor Dosyası:** Numara_ad_soyad_kotlin_final_rapor.**pdf**
- **Projenin Github Linki:** Projenizi githuba atıp linkini 3. item olarak ekleyeceksiniz.

## Proje Sunumu (%60 etkili)
- Final sınav takviminde belirtilen tarihte, belirtilen sınıfta projelerinizi sözlü olarak sunacaksınız.
- Sınav saati boyunca sınıfta olmanız zorunludur. Sunuma katılmayanlar, saati kaçıranlar vs, projesini gönderse dahi not olarak 0 alacaktır.
- Sunumlarınızı android emülatör veya Vysor programı aracılığı ile telefonunuzu ekrana yansıtarak yapabilirsiniz.
