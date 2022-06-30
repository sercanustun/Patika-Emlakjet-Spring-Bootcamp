# 1. Ödev - JAVA 8, JAVA 11 ve JAVA 17 ILE GELEN ÖZELLİKLER NELERDİR ?

Bu ödevde JAVA8, JAVA 11 ve JAVA 17 ile gelen önemli özelliklerin açıklamaları ve örnekleri yer almaktadır.

## İçindekiler
1. [JAVA 8 İLE GELEN ÖZELLER](#java-8-ile-gelen-ozellikler)
2. [JAVA 11 İLE GELEN ÖZELLER](#java-11-ile-gelen-ozellikler)
3. [JAVA 17 İLE GELEN ÖZELLİKLER](#java-17-ile-gelen-ozellikler)
4. [KAYNAKLAR](#kaynaklar)



## Java 8 Ile Gelen Ozellikler 

### - Default Metodlar

Java 8 öncesinde herhangi bir Interface ( Arayüz ) sınıfında **gövdesiz** metod yazmak mümkün değildi. Java 8 ile gelen **default** kelimesi ile artık Interface sınıflarda gövdeli metot yazmak mümkün. Şöyle ki;

**Hesapla.java**

```java
public interface Hesapla {
    Integer topla(Integer sayi1, Integer sayi2);
    
    default Integer kareAl(Integer sayi){
        return sayi * sayi ;
    }
}
```

Hesapla isimli Interface’imizi eğer implement/uygulamak istersek java bize *2* yol sunuyor. İlki *Anonymous Inner Class* olarak tanımlamak, ikincisi ise bu arayüzün implementasyonunu yapan bir sınıf oluşturmak.

1. *Anonymous Inner Class*

   ```java
   public class HesaplaDeneme {
       public static void main(String[] args) {
       
               Hesapla hesapla = new Hesapla() {
                   @Override
                   public Integer topla(Integer sayi1, Integer sayi2) {
                       return sayi1 + sayi2;
                   }
               };
               
               Integer toplam = hesapla.topla(5, 5);
               System.out.println("Toplama sonucu : " + sonuc1);
               
       }
   }
   ```

2.  *Interface Implementation Class*

   ```java
   public class HesaplaImplementation implements Hesapla {
   
       @Override
       public Integer topla(Integer sayi1, Integer sayi2) {
           return sayi1 + sayi2;
       }
   
       public static void main(String[] args) {
           HesaplaImplementation hesapla = new HesaplaImplementation();
           Integer toplam = hesapla.topla(10, 10);
           System.out.println(toplam);
       }
   }
   ```

   Görüldüğü gibi 2 durumda da **default** anahtar kelimesi ile oluşturduğumuz metodun gövdesini yazmak zorunda kalmadık. Dilersek bu metodunda gövdesini oluşturabilirdik.

### - Fonksiyonel Arayüz ( Functional Interface )

Lambda ifadelerini uygulayabilmek için tasarlanmış ve sadece bir tane abstract ( soyut) metoda sahip fonksiyonel bir arayüzdür. Özellikleri ;

- Maximum bir tane abstract metod bulundurabilir.
- İçerisinde birden fazla static ve default metodlar barındırabilir.

```java
@FunctionalInterface // Opsiyonel
public interface FunctionalInterfaceExample {

    Integer topla(Integer sayi1, Integer sayi2);

    static void selamVer(){
        System.out.println("Selam !");
    }
    
    default Integer kareAl(Integer sayi){
        return sayi * sayi ;
    }

}
```



### - Lambda İfadeleri

Lambda ifadeleri, kısaca kendi başlarına tanımlanabilen herhangi bir classa ait olmadan iş yapabilen fonksiyonlardır. Buradaki fonksiyon kelimesi Java’cılara biraz garip gelebilir bildiğimiz metod diyebiliriz. İki adet parametre alan ve bu bu parametreleri çarpan bir fonksiyonu matematik ile şu şekilde ifade edebiliriz.

**fonksiyon(x,y) -> x \* y**

Lambda bizlere daha okunabilir kod yazma imkanı ve yukarıda bahsetmiş olduğum *Functional Interface’lerin* implementasyonunu yapmamızda kolaylık sağlamakta.

*Unutulmamalıdır ki, lambda ifadeleri yalnızca Functional Interface’lerle kullanılabilmekte.*

Syntax olarak; **(argument-list)** **->** **{body}** şeklindedir.

**(argument-list)** kısmında Interface sınıfında ki metodumuz tipine göre eğer parametre almayan bir metod ise boş bırakılır, parametre alan bir metod ise bu parametreler buraya yazılıp argüman olarak metoda gönderilir.

**{body}** kısmında ise metodumuzun ne iş yapacağı bilgisini tanımlayabiliriz. Yani kısaca metod gövdesi.

![](https://farukgenc.com/wp-content/uploads/2019/07/lambdaIfadesi-1.png)

İlk olarak *parametre almayan geri değer döndürmeyen* soyut metod bulunduran yani Functional Interface’in lambda expression kullanarak implementasyonunu nasıl yapabiliriz ona bakalım.

```java

public class OtelOtomasyonu {

    public static void main(String[] args) {
        Resopsiyonist resopsiyonist = () -> { System.out.println("Selamlar"); };
        resopsiyonist.selamVer();
    }

}
```



```java
public interface Resopsiyonist {

    void selamVer();

}
```

*Parametre alıp geri değer döndürmeyen* bir metod için şu şekilde yapabiliriz;

```java
public class KonusanRobot {
    public static void main(String[] args) {

        Robot robot = (String isim) -> {System.out.println("Merhaba, " + isim );};
        robot.ismimiSoyle("Faruk");

    }
}
```

```java
public interface Robot {

    void ismimiSoyle(String isim);

}
```

*Parametre alan ve geri değer döndüren* bir fonksiyonumuz olduğunu düşünelim. Örneğin *Integer* tipinde iki sayı giriliyor ve bu girilen sayıları çarpıp geriye sonucu döndürüyor. **( fonksiyon(x,y) -> x \* y )**

```java
public interface Fonksiyon {

    Integer carp(Integer sayi1, Integer sayi2);

}
view rawFonksiyo
```

```java
public class HesapMakinesi {
    public static void main(String[] args) {

        Fonksiyon fonksiyon = (Integer sayi1, Integer sayi2) -> { return sayi1 * sayi2 ;};
        Integer sonuc = fonksiyon.carp(10, 10);
        System.out.println("Çarpım sonucu : " + sonuc);

    }
}
```



### - Metot Referansı

Java 8 öncesine kadar bir metoda parametre olarak ya primitive türde bir değişken ya da bir nesne gönderebiliyorduk. Artık bir metoda aynı formatta olan bir metodu referans alabileceğini söyleyebiliyoruz.

Tek soyut metoda sahip fonksiyonel arayüzlerle birlikte kullanılabilen *“Metot referans”* yöntemi bir lambda ifadesi gibi düşünülebilir.

Daha açık olmak gerekirse, bir lambda ifadesi tanımlanırken fonksiyonel arayüz sınıfımızda bulunan metodun tipini ve parametre alıp almamasını dikkate alıyorduk. Eğer bu yapıda olan herhangi bir metodumuz varsa bu metodu lambda ifadesi gibi kullanabiliyoruz.

Static olan ve olmayan metodlar için durum değişmekte. Syntax şu şekilde;
Static olan bir metod için referans verilirken ; *ClassName::MetodIsmı*
Static olmayan metod için ise ; *ObjectReferans::MetodIsmı*



```java
public interface Ogrenci {

    Double notHesapla(Double vizeSinavi, Double finalSinavi);

}
```

```java
public class Okul {
    public static void main(String[] args) {
        
        // Lambda ifadesi ile bu şekilde yapabiliyorduk.
        // Ogrenci ogrenci = (vizeNotu,finalNotu) -> ( vizeNotu * 0.4 + finalNotu * 0.6 );
        
        // Static olan bir metodu Sınıf üzerinden erişerek metod referansı olarak verebiliriz.
        // Ogrenci ogrenci = Okul::hesaplaStatic;
        
        // Static olmayan bir metodu nesne üzerinden erişerek referans verebiliriz.
        Okul okul = new Okul();
        Ogrenci ogrenci = okul::hesapla;
        
        Double not = ogrenci.notHesapla(40,50);
        System.out.println("Notunuz : " + not);      
    }
    
    // Static method
    public static Double hesaplaStatic(Double vizeNotu,Double finalNotu){
        return vizeNotu * 0.4 + finalNotu * 0.6;
    }
    
    // Non-static method
    public Double hesapla(Double vizeNotu,Double finalNotu){
        return vizeNotu * 0.4 + finalNotu * 0.6;
    }
    
}    
```





## Java 11 Ile Gelen Ozellikler 

#### - Launch Single-File Source-Code Programs – JEP 330

Java dosyasınını hızlıca derlemeden çalıştırma özelliği gelmiştir.

```java
java MerhabaJava.java
```

#### - Local-Variable Syntax for Lambda Parameters – JEP 323

Java 10 ile gelen var ifadesinin kapsamı genişletilerek lambda expressions ile kullanma imkanı gelmiştir.

```java
public class App {

    public static void main(String[] args) {
        IntStream.of(1, 2, 3, 5, 6, 7)
                .filter((var i) -> i % 3 == 0)
                .forEach(System.out::println);
    }

}
```

Ayrıca **final** anahtar kelimesi ile birlikte kullanılabilmektedir.

#### - Nest-Based Access Control – JEP 181

Sınıf içerisinde yer alan alt sınıfların derlenmesinde değişiklik yapılarak nest olarak adlandırılan bir yapı kurulmuştur.

```java
public class App {

    public static void main(String[] args) {
        Alt.yazdir();
    }

    static class Alt {
        private static void yazdir() {
            System.out.println("Yusuf SEZER");
        }
    }

}
```

Yukarıdaki kodlar derlenip javap aracı ile incelendiğinde NestMembers, NestHost alanları görünecektir.

#### - HTTP Client (Standard) – JEP 321

Java 9 ile birlikte gelen HTTP/2 Client Java 11 ile standart olarak **java.net.http** paketinde gelmektedir.

```java
public class App {

    public static void main(String[] args)
            throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create("https://www.yusufsezer.com/"))
                .GET()
                .build();

        HttpResponse response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

    }

}
```

Standart paketi kullanmak için **java.net.http;** modülünün **module-info.java** dosyasına eklenmesi gerekir.

#### - Remove the Java EE and CORBA Modules – JEP 320

Kaldırılan paketler aşağıda yer almaktadır.

1. java.xml.ws
2. java.xml.bind
3. java.activation
4. java.xml.ws.annotation
5. java.corba
6. java.transaction
7. java.se.ee
8. jdk.xml.ws
9. jdk.xml.bind

Java 11 ile birlikte XML işlemleri için JAXB paketinin JAR veya Maven olarak bağımlılık olarak eklenmesi gerekmektedir.

#### - String Methods

String işlemlerini kolaylaştırmak için yeni metotlar eklenmiştir.

##### **strip, stripLeading, stripTrailing** – trim metodundan farkı Unicode desteğinin olmasıdır.

```java
public class App {

    public static void main(String[] args) {
        var myName = "   Yusuf Sefa SEZER  ";
        System.out.println(myName.strip().length());
        var myName2 = "   Yusuf Sefa SEZER  ";
        System.out.println(myName2.stripLeading().length());
        System.out.println(myName2.stripTrailing().length());
    }

}
```

##### **isBlank** – String ifadesinin boşluk, Unicode boşluk değerine göre kontrol eder.

```java
public class App {

    public static void main(String[] args) {
        var myName = " \u205F ";
        System.out.println(myName.isBlank());  // içeriğe bakar
        System.out.println(myName.isEmpty());  // uzunluğa bakar
    }

}
```

##### **lines** – String ifadesindeki satır sonlandırıcına göre Stream API değeri verir.

```java
public class App {

    public static void main(String[] args) {
        var myName = "Yusuf\nSefa\nSEZER\r\n\ry";
        System.out.println(myName.lines().count());
    }

}
```

##### **repeat** – String ifadesini parametre değeri kadar tekrar eder.

```java
public class App {

    public static void main(String[] args) {
        var myName = "Yusuf Sefa SEZER\n";
        System.out.println(myName.repeat(3));
    }

}
```

#### - Collection

##### Koleksiyonları dizilere çevirmek için **toArray(IntFunction)** metodu eklenmiştir.

```java
public class App {

    public static void main(String[] args) {
        String[] myNames = List
                .of("Yusuf", "Sefa", "SEZER")
                .toArray(String[]::new);
        System.out.println(myNames.length);
    }

}
```

#### - Files

##### Dosya okuma ve yazma işlemlerini hızlıca yapmak için **readString, writeString** metotları eklenmiştir.

```java
public class App {

    public static void main(String[] args) throws IOException {
        // writeString
        Path tempFile = Files.writeString(
                Files.createTempFile("test", ".txt"), "Yusuf Sefa SEZER");
        System.out.println(tempFile);

        // readString
        String fileContent = Files.readString(tempFile);
        System.out.println(fileContent);
    }

}
```

#### - Path

##### Dosya/Dizin yolunu ifade etmek için kullanılan Path arayüzüne **of** metotları eklenmiştir.

```java
public class App {

    public static void main(String[] args) throws IOException {
        // of(string)
        Path pathString = Path.of("C:", "temp", "test.txt");
        System.out.println(pathString);
        System.out.println(Files.exists(pathString));

        // of(uri)
        URI uri = URI.create("file:///C:/temp/test.txt");
        System.out.println(uri);
        Path pathURI = Path.of(uri);
        System.out.println(pathURI);
        System.out.println(Files.exists(pathURI));
    }

}
```

#### - java.io

##### Java ile Input/Output işlemlerinde kullanılan java.io paketine yeni **nullInputStream, nullOutputStream, nullReader, nullWriter, readNBytes** metotları eklenmiştir.

```java
public class App {

    public static void main(String[] args) throws IOException {
        // nullReader
        Reader nullReader = Reader.nullReader();
        System.out.println(nullReader.read());

        // nullWriter
        Writer nullWriter = Writer.nullWriter();
        nullWriter.write("Yusuf SEZER");
    }

}
```

```java
public class App {

    public static void main(String[] args) throws IOException {
        var stream = new ByteArrayInputStream("Yusuf Sefa SEZER".getBytes());
        System.out.println(new String(stream.readNBytes(5)));
        stream.close();
    }

}
```

##### Java ile Input/Output işlemlerinde kullanılan java.io paketine yeni **nullInputStream, nullOutputStream, nullReader, nullWriter, readNBytes** metotları eklenmiştir.

#### - java.lang.Class

##### Nest-based yapısına göre derlenen sınıflar ile ilgili detaylı bilgi almak için Class sınıfına **getNestHost, getNestMembers, isNestmateOf** metotları eklenmiştir.

```java
public class App {

    public static void main(String[] args) {
        Class<?> clazz = App.class;
        System.out.println(clazz.getNestHost().getSimpleName());
        Arrays
                .stream(clazz.getNestMembers())
                .map(Class::getSimpleName)
                .forEach(System.out::println);
    }

    static class Alt {
        private static void yazdir() {
            System.out.println("Yusuf SEZER");
        }
    }

}
```

#### java.lang.Class

Java 11 ile geliştiricilerin işini kolaylaştıracak yeni metotlar eklemiş ve xml, corba gibi özellikler çıkarılmıştır.

Oracle JDK açık kaynak/ücretsiz sürüm ve Flight Recorder desteğini kaldırmıştır

Docker, Kubernetes gibi bulut tabanlı ortamlarda işlemleri hızlandırmak için JEP 309 Dynamic Class-File Constants, JEP 318 Epsilon: A No-Op Garbage Collector, JEP 315 Improve Aarch64 Intrinsics, JEP 333 ZGC: A Scalable Low-Latency Garbage Collector (Experimental) gibi alt yapısal düzenlemeler yapılmıştır.



## Java 17 Ile Gelen Ozellikler 

#### - Daima Katı Kayan Nokta Semantiğini Geri Yükle - JEP 306 

Bu JEP esas olarak bilimsel uygulamalar içindir ve kayan nokta işlemlerini sürekli olarak katı hale getirir. **Varsayılan kayan nokta işlemleri \*strict\* veya \*strictfp şeklindedir\* ve her ikisi de her platformda kayan nokta hesaplamalarından aynı sonuçları garanti eder.**

Java 1.2'den önce, *strictfp* davranışı da varsayılan davranıştı. Ancak, donanım sorunları nedeniyle mimarlar değişti ve bu tür davranışları yeniden etkinleştirmek için *strictfp anahtar sözcüğü gerekliydi.* Yani artık bu anahtar kelimeyi kullanmaya gerek yok.



#### - Gelişmiş Sözde Rastgele Sayı Üreticileri - JEP 356

Ayrıca daha özel kullanım durumları ile ilgili olarak, JEP 356, Sözde Rastgele Sayı Üreticileri (PRNG) için yeni arabirimler ve uygulamalar sağlar.

Bu nedenle, farklı algoritmaları birbirinin yerine kullanmak daha kolaydır ve ayrıca akış tabanlı programlama için daha iyi destek sunar:

```java
public IntStream getPseudoInts(String algorithm, int streamSize) {
    // returns an IntStream with size @streamSize of random numbers generated using the @algorithm
    // where the lower bound is 0 and the upper is 100 (exclusive)
    return RandomGeneratorFactory.of(algorithm)
            .create()
            .ints(streamSize, 0,100);
}
```

*Java.util.Random* , *SplittableRandom* ve *SecureRandom* gibi eski rastgele sınıflar artık yeni *RandomGenerator* arabirimini genişletiyor.



#### - Yeni macOS İşleme Ardışık Düzeni - JEP 382

Apple, Swing GUI'de dahili olarak kullanılan OpenGL API'sini (macOS 10.14'te) kullanımdan kaldırdığından bu JEP, macOS için bir Java 2D dahili işleme ardışık düzeni uygular. Yeni uygulama Apple Metal API'sini kullanıyor ve dahili motor dışında mevcut API'lerde herhangi bir değişiklik yapılmadı.

#### - macOS/AArch64 Bağlantı Noktası - JEP 391

Apple, bilgisayar hattını X64'ten AArch64'e geçirmek için uzun vadeli bir plan duyurdu. Bu JEP, JDK'yı macOS platformlarında AArch64 üzerinde çalışacak şekilde taşır.



#### - Kaldırma için Uygulama API'sını kullanımdan kaldırın - JEP 398

Bu, geliştirme kariyerlerine Applet API'lerini kullanarak başlayan birçok Java geliştiricisi için üzücü olsa da, birçok web tarayıcısı Java eklentileri desteğini zaten kaldırmıştır. API alakasız hale geldiğinden, bu sürüm, sürüm 9'dan beri kullanımdan kaldırılmış olarak işaretlenmiş olmasına rağmen, kaldırılmak üzere işaretlendi.

#### - Güçlü Kapsülleme JDK Dahili Parçaları - JEP 403

*JEP 403, –illegal-access* bayrağını kaldırdığından, JDK dahili öğelerini güçlü bir şekilde kapsüllemeye yönelik bir adım daha temsil eder . Platform bayrağı görmezden gelecek ve eğer bayrak varsa konsol, bayrağın durdurulduğunu bildiren bir mesaj yayınlayacaktır.

***Bu özellik, JDK kullanıcılarının sun.misc.Unsafe\* gibi kritik olanlar dışında dahili API'lere erişmesini engeller .**



#### - Anahtar için Model Eşleştirme (Önizleme) - JEP 406

*Bu, anahtar* ifadeleri ve deyimleri için kalıp eşleştirmeyi geliştirerek kalıp eşleştirmeye yönelik başka bir adımdır . Bu ifadeleri tanımlamak için gerekli olan kalıp levhasını azaltır ve dilin ifade edilebilirliğini artırır.

Yeni yeteneklerin iki örneğini görelim:

```java
static record Human (String name, int age, String profession) {}

public String checkObject(Object obj) {
    return switch (obj) {
        case Human h -> "Name: %s, age: %s and profession: %s".formatted(h.name(), h.age(), h.profession());
        case Circle c -> "This is a circle";
        case Shape s -> "It is just a shape";
        case null -> "It is null";
        default -> "It is an object";
    };
}

public String checkShape(Shape shape) {
    return switch (shape) {
        case Triangle t && (t.getNumberOfSides() != 3) -> "This is a weird triangle";
        case Circle c && (c.getNumberOfSides() != 0) -> "This is a weird circle";
        default -> "Just a normal shape";
    };
}
```



#### - RMI Aktivasyonunu Kaldır - JEP 407

Sürüm 15'te kaldırılmak üzere işaretlenen bu JEP, sürüm 17'de RMI etkinleştirme API'sini platformdan kaldırdı.



#### - Mühürlü Sınıflar - JEP 409

Mühürlü sınıflar [Project Amber'in bir parçasıdır ve bu JEP, JDK ](https://translate.google.com/website?sl=en&tl=tr&hl=tr&client=webapp&u=https://openjdk.java.net/projects/amber/)[15](https://translate.google.com/website?sl=en&tl=tr&hl=tr&client=webapp&u=https://openjdk.java.net/jeps/360) ve [16](https://translate.google.com/website?sl=en&tl=tr&hl=tr&client=webapp&u=https://openjdk.java.net/jeps/397) sürümlerinde önizleme modunda mevcut olmasına rağmen resmi olarak dile yeni bir özellik sunar .

Bu özellik, diğer sınıfların veya arabirimlerin kapalı bir bileşeni genişletebileceğini veya uygulayabileceğini kısıtlar. JEP 406 ile birleştirilmiş model eşleştirme ile ilgili başka bir gelişme göstermek, tip, döküm ve hareket kodu modelinin daha karmaşık ve daha temiz bir şekilde incelenmesine olanak sağlayacaktır.

Eylemde görelim:

```java
int getNumberOfSides(Shape shape) {
    return switch (shape) {
        case WeirdTriangle t -> t.getNumberOfSides();
        case Circle c -> c.getNumberOfSides();
        case Triangle t -> t.getNumberOfSides();
        case Rectangle r -> r.getNumberOfSides();
        case Square s -> s.getNumberOfSides();
    };
}
```



#### - Deneysel AOT ve JIT Derleyicisini kaldırın - JEP 410

Sırasıyla JDK 9 ve JDK 10'a deneysel özellikler olarak sunulan , GraalVM'den ( [JEP-317 ) Önde Gelen (AOT) derlemesi ( ](https://translate.google.com/website?sl=en&tl=tr&hl=tr&client=webapp&u=https://openjdk.java.net/jeps/317)[JEP 295](https://translate.google.com/website?sl=en&tl=tr&hl=tr&client=webapp&u=https://openjdk.java.net/jeps/295) ) ve Tam Zamanında (JIT) derleyicisi, yüksek bakım maliyeti.

Öte yandan, önemli bir kabul görmediler. Bu nedenle, bu JEP onları platformdan kaldırdı, ancak geliştiriciler yine de [GraalVM](https://translate.google.com/website?sl=en&tl=tr&hl=tr&client=webapp&u=https://www.graalvm.org/) kullanarak bunlardan yararlanabilir .



#### - Kaldırma için Güvenlik Yöneticisini Kullanımdan Kaldırın - JEP 411

İstemci tarafı Java kodunun güvenliğini sağlamayı amaçlayan güvenlik yöneticisi, artık alakalı olmadığı için kaldırılmak üzere işaretlenmiş bir başka özelliktir.



#### - Yabancı İşlev ve Bellek API'si (İnkübatör) - JEP 412

Yabancı İşlev ve Bellek API'si, Java geliştiricilerinin JVM dışından koda erişmesine ve yığının dışında belleği yönetmesine olanak tanır. Amaç, [JNI API'sini](https://translate.google.com/website?sl=en&tl=tr&hl=tr&client=webapp&u=https://docs.oracle.com/en/java/javase/16/docs/specs/jni/index.html) değiştirmek ve eskisine kıyasla güvenlik ve performansı artırmaktır.

[Bu API, Panama Projesi](https://translate.google.com/website?sl=en&tl=tr&hl=tr&client=webapp&u=https://openjdk.java.net/projects/panama/) tarafından geliştirilen başka bir özelliktir ve [393](https://translate.google.com/website?sl=en&tl=tr&hl=tr&client=webapp&u=https://openjdk.java.net/jeps/393) , [389](https://translate.google.com/website?sl=en&tl=tr&hl=tr&client=webapp&u=https://openjdk.java.net/jeps/389) , [383](https://translate.google.com/website?sl=en&tl=tr&hl=tr&client=webapp&u=https://openjdk.java.net/jeps/383) ve [370](https://translate.google.com/website?sl=en&tl=tr&hl=tr&client=webapp&u=https://openjdk.java.net/jeps/370) JEP'leri tarafından geliştirilmiş ve geçmiştir .

Bu özellik ile bir Java sınıfından bir C kütüphanesine çağrı yapabiliriz:

```java
private static final SymbolLookup libLookup;

static {
    // loads a particular C library
    var path = JEP412.class.getResource("/print_name.so").getPath();
    System.load(path);
    libLookup = SymbolLookup.loaderLookup();
}
```

Öncelikle, çağırmak istediğimiz hedef kitaplığı API üzerinden yüklememiz gerekiyor.

Ardından, hedef yöntemin imzasını belirlemeli ve son olarak onu çağırmalıyız:



```java
public String getPrintNameFormat(String name) {

    var printMethod = libLookup.lookup("printName");

    if (printMethod.isPresent()) {
        var methodReference = CLinker.getInstance()
            .downcallHandle(
                printMethod.get(),
                MethodType.methodType(MemoryAddress.class, MemoryAddress.class),
                FunctionDescriptor.of(CLinker.C_POINTER, CLinker.C_POINTER)
            );

        try {
            var nativeString = CLinker.toCString(name, newImplicitScope());
            var invokeReturn = methodReference.invoke(nativeString.address());
            var memoryAddress = (MemoryAddress) invokeReturn;
            return CLinker.toJavaString(memoryAddress);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
    throw new RuntimeException("printName function not found.");
}
```

#### - Vektör API (İkinci İnkübatör) - JEP 414

Vector API, paralel olarak yürütülen çeşitli talimat setleri anlamına gelen SIMD (Tek Yönerge, Çoklu Veri) tipi işlemle ilgilenir. Vektör talimatlarını destekleyen ve bu tür talimatların boru hatları gibi yürütülmesine izin veren özel CPU donanımından yararlanır.

Sonuç olarak, yeni API, geliştiricilerin temel donanımın potansiyelinden yararlanarak daha verimli kod uygulamalarına olanak tanıyacak.

Bu işlem için günlük kullanım örnekleri, bilimsel cebirsel doğrusal uygulamalar, görüntü işleme, karakter işleme ve herhangi bir ağır aritmetik uygulama veya birden çok bağımsız işlenen için bir işlem uygulaması gereken herhangi bir uygulamadır.

Basit bir vektör çarpma örneğini göstermek için API'yi kullanalım:

```java
public void newVectorComputation(float[] a, float[] b, float[] c) {
    for (var i = 0; i < a.length; i += SPECIES.length()) {
        var m = SPECIES.indexInRange(i, a.length);
        var va = FloatVector.fromArray(SPECIES, a, i, m);
        var vb = FloatVector.fromArray(SPECIES, b, i, m);
        var vc = va.mul(vb);
        vc.intoArray(c, i, m);
    }
}

public void commonVectorComputation(float[] a, float[] b, float[] c) {
    for (var i = 0; i < a.length; i ++) {
        c[i] = a[i] * b[i];
    }
}
```



#### - Bağlama Özel Seriyi Kaldırma Filtreleri - JEP 415

[İlk olarak JDK 9'da tanıtılan JEP 290](https://translate.google.com/website?sl=en&tl=tr&hl=tr&client=webapp&u=https://openjdk.java.net/jeps/290) , birçok güvenlik sorununun ortak kaynağı olan güvenilmeyen kaynaklardan gelen serileştirilmiş verileri doğrulamamızı sağladı. Bu doğrulama, JVM düzeyinde gerçekleşir ve daha fazla güvenlik ve sağlamlık sağlar.

JEP 415 ile uygulamalar, JVM düzeyinde tanımlanan bağlama özel ve dinamik olarak seçilen seri durumdan çıkarma filtrelerini yapılandırabilir. Her seri durumdan çıkarma işlemi bu tür filtreleri çağırır.





## Kaynaklar

#### 1

https://www.yusufsezer.com.tr/java-11/2

#### 2

https://www.baeldung.com/java-17-new-features3

#### 3

https://farukgenc.com/java/java-8-yenilikleri-bolum-1.html







