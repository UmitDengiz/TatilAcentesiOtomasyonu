# TatilAcentesiOtomasyonu
Tatil otomasyonu acentesi: Tatil sepeti benzeri, otel bilgileri, puanlama bilgileri, uyelik bilgileri

Hocam ilk defa bir grup ile bu şekilde proje geliştirdiğimiz için bir çok problemle hata ile karşılaştık bu bizim için bir tecrübe oldu 
ancak bazı sınıflar üzerinde ortak çalışmamız gerektiğinden dolayı commit noktasında çakışmalar oldu ve not supported hatası ile karşılaştık
bu hatayı toplu commit ederek aşabildik.

 Backend ve Frontend olmak üzere;

Mustafa Acar:Otel,Bilet,User(Zorunlu Tablo) sınıfları(Vize Final konuları Dahil şekilde) ve  login ve filter kısmını yaptım

Ümit Dengiz: Tur,Özellik ve Dosya(Zorunlu Tablo)yükleme sınıfları (Vize Final konuları Dahil şekilde) ve admin  template kısmını yaptım

Adem Kartal: Yorum,Role sınıfları  (Vize Final konuları Dahil şekilde) ve Admin tarafı kısmını yaptım

Ali Seydi Aydın:Kampanya,Rezervasyon,Privilege(Zorunlu Tablo) sınıflarını (Vize Final konuları Dahil şekilde) ve frontend template kısmını yaptım

Mantığa uygun olması için ilişkilerde kendi sınıfımız olmayan sınıflardanda yararlandık..

Frontend kısmındaki otel tur kampanya tablolarının detay kısımlarını  ortaklaşa bootstrap tan yararlanarak farklı hazır templatelere bakarak hazırlamaya çalıştık.

Mustafa Acar (5.Sınıf(mezun için internet Kalan son ders))
Ali seydi Aydın (5.Sınıf(mezun için internet Kalan son ders))
Adem Kartal(5.sınıf)
Ümit dengiz(4.sınıf)

Ayarlar:
Admin ve Frontend üye girişleri için
Admin Tarafı:
email=panel@gmail.com
şifre=1234
Frontend Tarafı:
email=adem@gmail.com
şifre=1234


Dosya yüklemenin uploadPath ayarları:
ImageServlet,OtelBean,TurBean, DosyaDao sınıflarında uploadPath dosyasının local bilgisayarınıza göre ayarlanması lazım
dosyalar Web klasörünün altındaki resources klasörünün de altındaki files klasöründe bulunmaktadır. yapı =\web\resources\files

