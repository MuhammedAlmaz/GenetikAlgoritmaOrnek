package gaornek;

public class GenetikAlgoritma {

    /* GA parameters */
    private static final double caprazlamaDegeri = 0.5;
    private static final double mustasyonDegeri = 0.065;
    private static final int secilecekKisiSayisi = 10;
    private static final boolean enIyiKoru = true;
    
    public static Populasyon evrimGecirt(Populasyon populasyon) {

    	Populasyon yeniPopulasyon= new Populasyon(populasyon.bireySayisi(), false);
    	
    	//en iyi bireyi koru , yani yeni populasyona en iyi bireyi ekleyelim ki ,
    	//en iyi deðerimizin üzerinden sonuca ulaþabilelim.
    	if (enIyiKoru) {
            yeniPopulasyon.bireyEkle(0, populasyon.enIyiBirey());
        }
    	
    	int baslangic=0;
    	if(enIyiKoru)
    	{
    		baslangic=1;
    	}
    	for (int i = baslangic; i < yeniPopulasyon.bireySayisi(); i++) {
            Birey birinciBirey = randomBireySec(populasyon);
            Birey ikinciBirey = randomBireySec(populasyon);
            Birey yeniBirey= caprazlamaYap(birinciBirey, ikinciBirey);
            yeniPopulasyon.bireyEkle(i, yeniBirey);
        }
    	
    	   // Mutate population
        for (int i = baslangic; i < yeniPopulasyon.bireySayisi(); i++) {
        	bireyiMutasyonaUgrat(yeniPopulasyon.bireyiGetir(i));
        }

        return yeniPopulasyon;
    	
    	
    	
    }
    

   //Çaprazlama iþleminin gerçekleþtirilmesi
    private static Birey caprazlamaYap(Birey birinciBirey, Birey ikinciBirey) {
        Birey caprazlananBirey = new Birey();
        // Tüm genleri dolaþarak caprazlama deðerinin altýnda veya üstünde ise 1. veya 2.
        //genlerdeki deðeri yeni genimize atayarak yeni bir birey yaratýyoruz...
        for (int i = 0; i < Baslangic.genAdedi; i++) {
            // Çaprazlama iþlemini gerçekleþtir.
            if (Math.random() <= caprazlamaDegeri) {
            	caprazlananBirey.geniDegistir(i, birinciBirey.geniGetir(i));
            } else {
            	caprazlananBirey.geniDegistir(i, ikinciBirey.geniGetir(i));
            }
        }
        return caprazlananBirey;
    }

    // Bireyler tekrar etmemesi için mutasyon geçirtmek gerekiyor.
    // Sonsuz döngüden veya ayný þeylerin tekrarlamasýnýn önüne geçmek için mutasyon geçirmeliyiz.
    private static void bireyiMutasyonaUgrat(Birey birey) {
        // Tüm genleri tek tek dolaþýyoruz
        for (int i = 0; i < Baslangic.genAdedi; i++) {
            if (Math.random() <= mustasyonDegeri) {
                // Yeni bir gen oluþturuyoruz (Gen 1 de gelebilir 0 da gelebilir.)
            	//Istenilirse o anki gen deðeri 0 ise 1 e veya 1 ise 0 a çevirilebiliriz.
            	//Fakat gen deðerini random atamayý tercih ettim ben.
                birey.geniDegistir(i, (byte) Math.round(Math.random()));
            }
        }
    }

    // Bir birey topluluðu oluþturacaðýz random,
    // Bu oluþan bireylerin arasýndaki en iyi bireyleri secip yeni populasyonumuza ekleyeceðiz.
    // Populasyonun kisi sayisini genelde toplam populasyon/50 þeklinde seçmek gerekiyor.
    private static Birey randomBireySec(Populasyon populasyon) {
        // secilen populasyonumuzu oluþturuyoruz
    	Populasyon secilenPopulasyon = new Populasyon(secilecekKisiSayisi, false);
        // kaç tane birey ekleyecek isek. Belirtilen sayý kadar populasyona birey ekliyoruz.
    	// Seçilen bireyler rastgele seçilen bireylerdir.
    	// Zamanla deðiþebileceði için Algoritma her seferinde ayný sonucu vermeyecektiz.
    	// Buradaki rastsallýðý kaldýrýp kendi kurgunuzu yapabilirsiniz..
        for (int i = 0; i < secilecekKisiSayisi; i++) {
            int secilenBireyinSirasi = (int) (Math.random() * populasyon.bireySayisi());
            secilenPopulasyon.bireyEkle(i, populasyon.bireyiGetir(secilenBireyinSirasi));
        }
        return secilenPopulasyon.enIyiBirey();
    }

}
